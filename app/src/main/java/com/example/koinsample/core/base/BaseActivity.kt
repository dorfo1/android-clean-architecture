package com.example.koinsample.core.base

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

abstract class BaseActivity : AppCompatActivity(), CoroutineScope by MainScope() {
}