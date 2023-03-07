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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.medetzhakupov.domain.model.RijksDataUIModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun RijksCollectionScreen(
    navigateToDetail: (objectNumber: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: RijksCollectionViewModel = hiltViewModel()

    when (val viewState = viewModel.viewState.collectAsStateWithLifecycle().value) {
        RijksCollectionViewState.Error -> Error(Modifier.fillMaxHeight())
        is RijksCollectionViewState.Loaded -> {
            RijksCollectionList(
                rijksDataUIModelList = viewState.rijksDataUIModelList,
                showPageLoading = viewState.loadingMore,
                onLoadMore = { viewModel.loadMoreRijksCollection() },
                navigateToDetail = navigateToDetail,
                modifier = modifier,
            )
        }
        RijksCollectionViewState.Loading -> Loading(Modifier.fillMaxHeight())
    }
}

@Composable
private fun Error(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
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
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RijksCollectionList(
    rijksDataUIModelList: List<RijksDataUIModel>,
    showPageLoading: Boolean,
    onLoadMore: () -> Unit,
    navigateToDetail: (objectNumber: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()

    listState.OnBottomReached(buffer = 4) { onLoadMore() }

    LazyColumn(
        state = listState,
        modifier = modifier,
    ) {
        item {
            SmallTopAppBar(
                title = {
                    Text(
                        text = "Rijks Collection",
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                modifier = Modifier
            )
        }
        rijksDataUIModelList.forEach { (artist, collection) ->
            stickyHeader {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = artist,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

            items(
                items = collection,
                key = { it.id }
            ) { item ->
                RijksCollectionItem(
                    item = item,
                    onItemClick = navigateToDetail,
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
