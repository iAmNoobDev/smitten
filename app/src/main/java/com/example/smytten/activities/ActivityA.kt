package com.example.smytten.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.example.smytten.ui.composables.CommonDialog

class ActivityA: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CommonDialog()
            Text(text = "Activity A")
        }
    }

}