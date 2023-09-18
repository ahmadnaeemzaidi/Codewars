package com.example.mycodingchallenge.ui.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeAppBar(
    title: String,
    modifier: Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = White
            )
                },
        modifier = modifier,
        backgroundColor = Black
    )
}

@Preview
@Composable
fun HomeAppBarPreview() {
    HomeAppBar(title = "Epic World",Modifier.fillMaxWidth().height(50.dp))
}