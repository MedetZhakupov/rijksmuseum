package dev.medetzhakupov.rijksmuseum.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.medetzhakupov.rijksmuseum.ui.rijkscollection.RijksCollectionScreen
import dev.medetzhakupov.rijksmuseum.ui.rijkscollection.RijksCollectionViewModel
import dev.medetzhakupov.rijksmuseum.ui.theme.RijksMuseumTheme

@SuppressLint("RestrictedApi")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: RijksCollectionViewModel by viewModels()

    @OptIn(ExperimentalLifecycleComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "rijkscollection") {
                composable("rijkscollection") {
                    val viewState = viewModel.viewState.collectAsStateWithLifecycle()

                    RijksMuseumTheme {
                        RijksCollectionScreen(
                            viewState = viewState.value,
                            onLoadMore = { viewModel.loadMoreRijksCollection() },
                        )
                    }
                }
                composable("artistsart") {

                }
            }
        }
    }
}
