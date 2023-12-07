package com.example.smytten.repo

import android.app.Application
import com.example.smytten.dto.ContentDTO
import com.example.smytten.utils.JsonUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepository @Inject constructor(private val application: Application) {

    fun getHomeListing(): Flow<List<ContentDTO>?> {
        return flow { emit(JsonUtils.getHomeList(context = application.applicationContext)?.content) }
    }

}