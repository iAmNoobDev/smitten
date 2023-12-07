package com.example.smytten.ui.composables

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.smytten.R
import com.example.smytten.utils.BroadCastAction
import com.example.smytten.utils.SystemBroadcastReceiver

@Composable
fun CommonDialog() {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    SystemBroadcastReceiver(
        systemAction = BroadCastAction.SHOW_DIALOG,
        onSystemEvent = { intent ->
            if (intent != null) {
                showDialog = intent.getBooleanExtra("show", false)
            }
        }
    )
    if (showDialog) {
        Dialog(
            onDismissRequest = {
                context.sendBroadcast(
                    Intent().apply {
                        action = BroadCastAction.SHOW_DIALOG
                        putExtra("show", false)
                    }
                )
                showDialog = false
            }
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Text(text = stringResource(R.string.smytten), fontSize = 24.sp)
                        Text(text = stringResource(R.string.hello))
                        Button(
                            onClick = {
                                context.sendBroadcast(
                                    Intent().apply {
                                        action = BroadCastAction.SHOW_DIALOG
                                        putExtra("show", false)
                                    }
                                )
                                showDialog = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = stringResource(R.string.ok))
                        }
                    }
                }
            }
        }
    }
}