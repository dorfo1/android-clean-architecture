package com.example.koinsample.core.extensions

import android.content.Context
import android.view.View
import android.widget.Toast
import com.example.koinsample.R
import org.koin.core.scope.Scope
import retrofit2.Retrofit

inline fun <reified T>Scope.resolveRetrofitService() : T{
    val retrofit : Retrofit = get()
    return retrofit.create(T::class.java)
}

fun String.setSizeToUrl(width : String = "285", height : String = "300") : String{
    var newUrl = this.replace("{width}",width)
    newUrl = newUrl.replace("{height}",height)
    return newUrl
}

fun String.setSizeToVideoThumbnail(width : String = "285", height : String = "300") : String{
    var newUrl = this.replace("%{width}",width)
    newUrl = newUrl.replace("%{height}",height)
    return newUrl
}
fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.hide(){
    this.visibility = View.GONE
}

fun Context.showBaseError(){
    Toast.makeText(this,getString(R.string.default_error_message),Toast.LENGTH_LONG).show()
}