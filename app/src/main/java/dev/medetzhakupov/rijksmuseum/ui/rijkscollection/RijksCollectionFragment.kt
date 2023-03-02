package dev.medetzhakupov.rijksmuseum.ui.rijkscollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dagger.hilt.android.AndroidEntryPoint
import dev.medetzhakupov.domain.data.model.ArtObject
import dev.medetzhakupov.rijksmuseum.ui.theme.RijksMuseumTheme

@AndroidEntryPoint
class RijksCollectionFragment : Fragment() {

    private val viewModel: RijksCollectionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
//                RijksMuseumTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        pagingRijksCollection()
                    }
//                }
            }
        }
    }

    @Composable
    fun pagingRijksCollection() {
        val artObjects = viewModel.getRijksCollection().collectAsLazyPagingItems()

        LazyColumn {
            items(
                items = artObjects,
                key = { item: ArtObject -> item.id }
            ) { art ->
                Text(
                    modifier = Modifier
                        .height(75.dp),
                    text = art?.title ?: "",
                )

                Divider()
            }

            when (val state = artObjects.loadState.refresh) { //FIRST LOAD
                is LoadState.Error -> {
                    //TODO Error Item
                    //state.error to get error message
                }
                is LoadState.Loading -> { // Loading UI
                    item {
                        Column(
                            modifier = Modifier
                                .fillParentMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(8.dp),
                                text = "Refresh Loading"
                            )

                            CircularProgressIndicator(color = Color.Black)
                        }
                    }
                }
                else -> {}
            }

            when (val state = artObjects.loadState.append) { // Pagination
                is LoadState.Error -> {
                    //TODO Pagination Error Item
                    //state.error to get error message
                }
                is LoadState.Loading -> { // Pagination Loading UI
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(text = "Pagination Loading")

                            CircularProgressIndicator(color = Color.Black)
                        }
                    }
                }
                else -> {}
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        RijksMuseumTheme {
            pagingRijksCollection()
        }
    }
}