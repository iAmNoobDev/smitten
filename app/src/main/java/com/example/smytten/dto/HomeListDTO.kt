package com.example.smytten.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeListDTO(
    val content: List<ContentDTO>
)
