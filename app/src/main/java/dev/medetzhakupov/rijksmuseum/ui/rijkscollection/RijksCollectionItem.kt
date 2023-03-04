package dev.medetzhakupov.rijksmuseum.ui.rijkscollection

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.medetzhakupov.domain.model.ArtistCollectionItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RijksCollectionItem(
    item: ArtistCollectionItem,
    onItemClick: (objectNumber: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { onItemClick(item.objectNumber) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = modifier.fillMaxWidth()) {
            AsyncImage(
                contentScale = ContentScale.FillHeight,
                model = item.headerImage.url,
                contentDescription = item.longTitle,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
            )

            Row(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}