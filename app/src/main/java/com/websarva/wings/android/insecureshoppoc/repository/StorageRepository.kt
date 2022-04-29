package com.websarva.wings.android.insecureshoppoc.repository

import android.content.Context
import android.util.Log
import java.io.*
import java.lang.Exception
import javax.inject.Inject

interface StorageRepository {
    fun startLog(context: Context)
    fun finishLog()
}

class StorageRepositoryClient @Inject constructor(): StorageRepository {
    private var logThread: WriteLogThread? = null

    override fun startLog(context: Context) {
        if (logThread == null){
            logThread = WriteLogThread(context).apply {
                start()
            }
        }
    }

    override fun finishLog() {
        if (logThread != null){
            logThread!!.interrupt()
        }
    }
}

class WriteLogThread(
    private val context: Context
): Thread(){
    override fun run() {
        var reader: BufferedReader? = null
        var writer: PrintWriter? = null

        val pId = android.os.Process.myPid().toString()

        try {
            val proc = Runtime.getRuntime().exec(arrayOf("logcat", "-v", "time"))
            reader = BufferedReader(InputStreamReader(proc.inputStream), 1024)
            var line: String
            while (true){
                line = reader.readLine()
                if (line.isEmpty()){
                    try {
                        sleep(200)
                    }catch (e: InterruptedException){
                        Log.w("Warning", e.message.toString())
                    }
                    continue
                }

                if (line.indexOf(pId) != 1){
                    try {
                        val out = context.openFileOutput("log.txt", Context.MODE_PRIVATE or Context.MODE_APPEND)
                        writer = PrintWriter(OutputStreamWriter(out, "UTF-8")).apply {
                            println(line)
                        }
                    }catch (e: Exception){
                        Log.e("Error", e.message.toString())
                    }finally {
                        writer?.close()
                    }
                }
            }
        }catch (e: IOException){
            Log.e("Error", e.message.toString())
        }finally {
            if (reader != null){
                try {
                    reader.close()
                }catch (e: IOException){
                    Log.e("Error", e.message.toString())
                }
            }
        }
    }
}