package com.example.smytten.utils

import android.content.Context
import com.example.smytten.dto.HomeListDTO
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object JsonUtils {

    fun getHomeList(context: Context): HomeListDTO? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val listType = Types.newParameterizedType(HomeListDTO::class.java, List::class.java)
        val adapter: JsonAdapter<HomeListDTO> = moshi.adapter(listType)
        val file = "smytten.json"
        val json = context.assets.open(file).bufferedReader().use { it.readText() }
        return try {
            adapter.fromJson(json)
        } catch (e: Exception) {
            null
        }
    }
}