package dev.medetzhakupov.rijksmuseum.ui.rijkscollection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.medetzhakupov.domain.model.RijksCollection

@Composable
fun RijksCollectionScreen(
    viewState: RijksCollectionViewState,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (viewState) {
        RijksCollectionViewState.Error -> Error(Modifier.fillMaxHeight())
        is RijksCollectionViewState.Loaded -> {
            RijksCollectionList(
                rijksCollectionList = viewState.rijksCollectionList,
                showPageLoading = viewState.loadingMore,
                onLoadMore = onLoadMore,
                modifier = modifier,
            )
        }
        RijksCollectionViewState.Loading -> Loading(Modifier.fillMaxHeight())
    }
}

@Composable
private fun Error(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Oops... Something went wrong"
        )
    }
}


@Composable
private fun Loading(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator()
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun RijksCollectionList(
    rijksCollectionList: List<RijksCollection>,
    showPageLoading: Boolean,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()

    listState.OnBottomReached(buffer = 4) { onLoadMore() }

    LazyColumn(
        state = listState,
        modifier = modifier.background(color = MaterialTheme.colorScheme.inverseOnSurface),
    ) {
        item {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Rijks Collection")
                },
                modifier = Modifier
            )
        }
        rijksCollectionList.forEach { (artist, collection) ->
            stickyHeader {
                Card(
                    shape = RectangleShape,
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row {
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = artist,
                            fontSize = 24.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            }

            items(
                items = collection,
                key = { it.id }
            ) { item ->
                RijksCollectionItem(
                    imageUrl = item.headerImage.url,
                    text = item.title,
                    modifier = Modifier.wrapContentHeight()
                )
            }
        }

        if (showPageLoading) {
            item {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun LazyListState.OnBottomReached(
    // tells how many items before we reach the bottom of the list
    // to call onLoadMore function
    buffer: Int = 0,
    onLoadMore: () -> Unit
) {
    // Buffer must be positive.
    // Or our list will never reach the bottom.
    require(buffer >= 0) { "buffer cannot be negative, but was $buffer" }

    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?: return@derivedStateOf true

            // subtract buffer from the total items
            lastVisibleItem.index >= layoutInfo.totalItemsCount - 1 - buffer
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .collect { if (it) onLoadMore() }
    }
}
