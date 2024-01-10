package com.example.thindie.leenotes

import android.util.Log

fun Any?.Q(vararg any: Any?) {

    val messageString = any.joinToString(separator = " ") {
        it.toString()
    }

    if (this != null) {
        Log.d("SERVICE_TAG", "${this::class.simpleName}  $messageString")
    } else {
        Log.d("SERVICE_TAG", "function :: $messageString}")
    }
}

fun QFun(vararg any: Any?) {
    null.Q(any)
}
