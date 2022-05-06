package com.websarva.wings.android.insecureshoppoc.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import java.lang.StringBuilder
import javax.inject.Inject

interface PlatformRepository {
    fun dump(context: Context, uri: Uri, listener: (result: String) -> Unit)
}

class PlatformRepositoryClient @Inject constructor(): PlatformRepository {
    override fun dump(context: Context, uri: Uri, listener: (result: String) -> Unit) {
        try {
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
        }catch (e: Exception){
            Log.e("Error", e.message.toString())
            listener("")
        }
    }
}