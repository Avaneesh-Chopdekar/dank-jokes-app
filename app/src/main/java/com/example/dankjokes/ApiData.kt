package com.example.dankjokes

import androidx.annotation.Keep

@Keep
data class ApiData(
    val joke: String,
    val category: String,
    val delivery: String,
    val error: Boolean,
    val flags: Flags,
    val id: Int,
    val lang: String,
    val safe: Boolean,
    val setup: String,
    val type: String
)