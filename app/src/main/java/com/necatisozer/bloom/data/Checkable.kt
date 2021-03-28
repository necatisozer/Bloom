package com.necatisozer.bloom.data

data class Checkable<T>(
    val data: T,
    val checked: Boolean = false,
)