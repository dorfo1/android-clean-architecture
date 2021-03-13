package com.example.koinsample.core.extensions

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.koinsample.R
import com.example.koinsample.core.base.Resource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.koin.core.scope.Scope
import retrofit2.Retrofit
import java.lang.Exception

inline fun <reified T> Scope.resolveRetrofitService(): T {
    val retrofit: Retrofit = get()
    return retrofit.create(T::class.java)
}

fun String.setSizeToUrl(width: String = "285", height: String = "300"): String {
    var newUrl = this.replace("{width}", width)
    newUrl = newUrl.replace("{height}", height)
    return newUrl
}

fun String.setSizeToVideoThumbnail(width: String = "285", height: String = "300"): String {
    var newUrl = this.replace("%{width}", width)
    newUrl = newUrl.replace("%{height}", height)
    return newUrl
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun Context.showBaseError() {
    Toast.makeText(this, getString(R.string.default_error_message), Toast.LENGTH_LONG).show()
}


fun <T> Flow<T>.toFlowResource(): Flow<Resource<T>> = flow {
    onStart { emit(Resource.Loading<T>()) }
        .onEach { emit(Resource.Success(it)) }
        .catch { emit(Resource.Error<T>(Exception(it))) }
        .collect()
}


fun <T,O> Flow<T>.asResource(map : (T) -> O): Flow<Resource<O>> = flow {
    onStart { emit(Resource.Loading<O>()) }
        .map { map.invoke(it) }
        .onEach { emit(Resource.Success(it)) }
        .catch { emit(Resource.Error<O>(Exception(it))) }
        .collect()
}







