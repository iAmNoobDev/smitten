package com.example.smytten

import android.app.Application
import android.content.Intent
import com.example.smytten.utils.BroadCastAction
import dagger.hilt.android.HiltAndroidApp
import java.util.Timer
import kotlin.concurrent.schedule

@HiltAndroidApp
class SmyttenApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        Timer().schedule(5000) {
            sendBroadcast(
                Intent().apply {
                    action = BroadCastAction.SHOW_DIALOG
                    putExtra("show", true)
                }
            )
        }
    }

}