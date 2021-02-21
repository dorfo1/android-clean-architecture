package com.example.koinsample.core.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.koin.core.KoinComponent

abstract class HubView<VM : ViewModel>(
    private val context:Context,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: VM
) : KoinComponent, CoroutineScope by MainScope(){

    var view : View? = null
    var root : View? = null

    @LayoutRes
    abstract fun getLayout() : Int

    abstract fun onViewCreated()

    fun inflate(root : ViewGroup){
        this.root = root
        view = LayoutInflater.from(context).inflate(getLayout(),null)
        root.addView(view)
        onViewCreated()
    }
}