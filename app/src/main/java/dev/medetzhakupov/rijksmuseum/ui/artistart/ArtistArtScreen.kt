package dev.medetzhakupov.rijksmuseum.ui.artistart

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import dev.medetzhakupov.domain.data.model.ArtObjectDetail

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ArtistArtScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ArtistArtViewModel = hiltViewModel()
) {
    when (val viewState = viewModel.viewState.collectAsStateWithLifecycle().value) {
        ArtistArtViewState.Error -> Error(Modifier.fillMaxHeight())
        is ArtistArtViewState.Loaded -> {
            ArtObjectDetails(
                artObjectDetail = viewState.artist,
                onBackClick = onBackClick,
                modifier = modifier,
            )
        }
        ArtistArtViewState.Loading -> Loading(Modifier.fillMaxHeight())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun ArtObjectDetails(
    artObjectDetail: ArtObjectDetail,
    onBackClick:() -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onBackClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) {
        Column(modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = artObjectDetail.webImage.url,
                contentDescription = artObjectDetail.title,
                modifier = Modifier
                    .height(300.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = artObjectDetail.title,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Text(
                text = "${artObjectDetail.productionPlaces.firstOrNull()}, ${artObjectDetail.dating.yearEarly} - ${artObjectDetail.dating.yearLate}" ,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = artObjectDetail.description,
                style = MaterialTheme.typography.bodyLarge,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Details",
                style = MaterialTheme.typography.headlineSmall,
            )

            Spacer(modifier = Modifier.height(16.dp))

            DetailRow(title = "Original Title", detail = artObjectDetail.title)
            DetailRow(title = "Date Created", detail = "${artObjectDetail.dating.yearEarly} - ${artObjectDetail.dating.yearLate}")
            DetailRow(title = "Provenance", detail = artObjectDetail.productionPlaces.first { it.isNotBlank() })
            DetailRow(title = "Materials", detail = artObjectDetail.materials.filter { it.isNotBlank() }.joinToString())
            DetailRow(
                title = "Dimensions",
                detail =artObjectDetail.subTitle
            )
            DetailRow(title = "Type", detail = artObjectDetail.objectTypes.filter { it.isNotBlank() }.joinToString())

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Artist",
                style = MaterialTheme.typography.headlineSmall,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = artObjectDetail.principalOrFirstMaker,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun DetailRow(title: String, detail: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(.3f),
        )

        Text(
            text = detail,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 2,
            modifier = Modifier.weight(.7f),
            overflow = TextOverflow.Ellipsis,
        )
    }

    Spacer(modifier = Modifier.height(8.dp))
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