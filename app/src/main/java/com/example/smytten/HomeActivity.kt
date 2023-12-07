package com.example.smytten

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.smytten.activities.ActivityA
import com.example.smytten.activities.ActivityB
import com.example.smytten.activities.ActivityC
import com.example.smytten.activities.ActivityD
import com.example.smytten.activities.ActivityE
import com.example.smytten.activities.ActivityF
import com.example.smytten.ui.composables.CommonDialog
import com.example.smytten.utils.ScreenName
import com.example.smytten.utils.ViewType
import com.example.smytten.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val homeViewModel = viewModel<HomeViewModel>()
            val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
            CommonDialog()
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                modifier = Modifier.fillMaxSize(),
                content = {
                    when (val state = uiState) {
                        is HomeViewModel.UiState.Loading -> {
                            item {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) { CircularProgressIndicator(strokeWidth = 4.dp) }
                            }
                        }

                        is HomeViewModel.UiState.Data -> {
                            state.data?.let {
                                items(it) { content ->
                                    LazyRow(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentPadding = PaddingValues(horizontal = 4.dp),
                                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                                    ) {
                                        items(items = content.data) {
                                            Card(
                                                elevation = CardDefaults.elevatedCardElevation(0.dp),
                                                modifier = Modifier.size(
                                                    width = 170.dp,
                                                    height = 280.dp
                                                )
                                            ) {
                                                Column(
                                                    verticalArrangement = Arrangement.spacedBy(4.dp),
                                                    modifier = Modifier
                                                        .padding(6.dp)
                                                        .fillMaxWidth()
                                                ) {
                                                    AsyncImage(
                                                        model = it.image,
                                                        contentDescription = null,
                                                        contentScale = ContentScale.FillWidth,
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .weight(1f)
                                                    )
                                                    Row(
                                                        horizontalArrangement = Arrangement.spacedBy(
                                                            4.dp
                                                        )
                                                    ) {
                                                        it.brand?.let { brand ->
                                                            Text(
                                                                text = brand,
                                                                maxLines = 1,
                                                                overflow = TextOverflow.Ellipsis,
                                                                modifier = Modifier.weight(1f)
                                                            )
                                                        }
                                                        Text(
                                                            text = it.name,
                                                            maxLines = 1,
                                                            overflow = TextOverflow.Ellipsis,
                                                            modifier = Modifier.weight(1f)
                                                        )
                                                    }
                                                    Button(onClick = {
                                                        when (content.type) {
                                                            ViewType.PRODUCT -> {
                                                                homeViewModel.addOrRmFromCart(id = it.id)
                                                            }

                                                            ViewType.BUTTON -> {
                                                                when (it.name) {
                                                                    ScreenName.ActivityA -> {
                                                                        startActivity(
                                                                            Intent(
                                                                                this@HomeActivity,
                                                                                ActivityA::class.java
                                                                            )
                                                                        )
                                                                    }

                                                                    ScreenName.ActivityB -> {
                                                                        startActivity(
                                                                            Intent(
                                                                                this@HomeActivity,
                                                                                ActivityB::class.java
                                                                            )
                                                                        )
                                                                    }

                                                                    ScreenName.ActivityC -> {
                                                                        startActivity(
                                                                            Intent(
                                                                                this@HomeActivity,
                                                                                ActivityC::class.java
                                                                            )
                                                                        )
                                                                    }

                                                                    ScreenName.ActivityD -> {
                                                                        startActivity(
                                                                            Intent(
                                                                                this@HomeActivity,
                                                                                ActivityD::class.java
                                                                            )
                                                                        )
                                                                    }

                                                                    ScreenName.ActivityE -> {
                                                                        startActivity(
                                                                            Intent(
                                                                                this@HomeActivity,
                                                                                ActivityE::class.java
                                                                            )
                                                                        )
                                                                    }

                                                                    ScreenName.ActivityF -> {
                                                                        startActivity(
                                                                            Intent(
                                                                                this@HomeActivity,
                                                                                ActivityF::class.java
                                                                            )
                                                                        )
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }) {
                                                        Box(
                                                            modifier = Modifier.fillMaxWidth(),
                                                            contentAlignment = Alignment.Center
                                                        ) {
                                                            Text(
                                                                text = when (content.type) {
                                                                    ViewType.PRODUCT -> {
                                                                        getString(
                                                                            if (it.added) R.string.in_cart
                                                                            else R.string.add_to_cart
                                                                        )
                                                                    }

                                                                    ViewType.BUTTON -> {
                                                                        it.name
                                                                    }
                                                                }
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}