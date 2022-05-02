package com.websarva.wings.android.insecureshoppoc.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder
import javax.inject.Inject

interface StorageRepository {
    fun startLog(context: Context)
    fun finishLog()
    fun dump(context: Context, uri: Uri, listener: (result: String) -> Unit)
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

    override fun dump(context: Context, uri: Uri, listener: (result: String)->Unit){
        context.contentResolver.query(uri, null, null, null, null).use {
            if (it!!.moveToFirst()){
                do {
                    val sb = StringBuilder()
                    for (i in 0 until it.columnCount){
                        if (sb.isNotEmpty()){
                            sb.append(", ")
                        }
                        sb.append(it.getColumnName(i).toString() + " = " + it.getString(i))
                    }
                    listener(sb.toString())
                }while (it.moveToNext())
            }
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