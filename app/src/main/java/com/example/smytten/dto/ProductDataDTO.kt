package com.example.smytten.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDataDTO(
    val id: Long,
    val brand: String?,
    val name: String,
    val image: String?,
    val added: Boolean = false
)