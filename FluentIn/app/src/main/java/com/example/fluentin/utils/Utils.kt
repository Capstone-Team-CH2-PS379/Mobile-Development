package com.example.fluentin.utils

import android.text.TextUtils
import android.view.View

fun String.isEmailValid(): Boolean  {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}


fun View.show(){
    visibility = View.VISIBLE
}
fun View.gone(){
    visibility = View.GONE
}
