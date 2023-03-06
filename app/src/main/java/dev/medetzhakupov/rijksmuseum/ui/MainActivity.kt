package dev.medetzhakupov.rijksmuseum.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.medetzhakupov.rijksmuseum.ui.artistart.ArtistArtScreen
import dev.medetzhakupov.rijksmuseum.ui.rijkscollection.RijksCollectionScreen
import dev.medetzhakupov.rijksmuseum.ui.theme.RijksMuseumTheme

@SuppressLint("RestrictedApi")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            RijksMuseumTheme {
                NavHost(
                    navController = navController,
                    startDestination = Route.COLLECTION
                ) {
                    composable(Route.COLLECTION) {
                        RijksCollectionScreen(
                            navigateToDetail = {
                                navController.navigate("${Route.ARTIST_COLLECTION_ITEM}/$it")
                            },
                            modifier = Modifier,
                        )
                    }
                    composable("${Route.ARTIST_COLLECTION_ITEM}/{objectNumber}") {
                        ArtistArtScreen(
                            modifier = Modifier,
                            onBackClick = { navController.navigateUp() }
                        )
                    }
                }
            }
        }
    }
}

object Route {
    const val COLLECTION = "Collection"
    const val ARTIST_COLLECTION_ITEM = "ArtistCollectionItem"
}
