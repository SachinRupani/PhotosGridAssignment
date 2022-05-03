package com.obvious.photosgridassignment

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object Moshi {
    val moshiInstance: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}