package com.example.koinsample.core.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

abstract class BaseFragment(@LayoutRes layout : Int) : Fragment(layout) ,CoroutineScope by MainScope(){
}