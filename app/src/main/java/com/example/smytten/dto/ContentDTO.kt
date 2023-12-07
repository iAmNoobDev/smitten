package com.example.smytten.dto

import com.example.smytten.utils.ViewType
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContentDTO(
    val type: ViewType,
    val data: List<ProductDataDTO>
)
