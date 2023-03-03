package dev.medetzhakupov.rijksmuseum.ui.rijkscollection

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RijksCollectionItem(
    imageUrl: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = modifier.fillMaxWidth()) {
            AsyncImage(
                contentScale = ContentScale.FillHeight,
                model = imageUrl,
                contentDescription = imageUrl,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
            )

            Row(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}