package com.example.culturecurious.data.remote.dto

data class NameDto(
    val common: String,
    val official: String,
    val nativeName: Map<String, NativeNameDto>
)
