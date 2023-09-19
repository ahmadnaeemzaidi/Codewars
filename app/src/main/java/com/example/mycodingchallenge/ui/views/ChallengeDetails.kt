package com.example.mycodingchallenge.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mycodingchallenge.LoadingItem
import com.example.mycodingchallenge.R
import com.example.mycodingchallenge.data.response.ChallengeDetailsResponse
import com.example.mycodingchallenge.utils.ErrorDialog
import com.example.mycodingchallenge.viewmodel.ChallengesViewModel


@Composable
fun ChallengeDetailsScreen(
    challengeId: String,
    challengesViewModel: ChallengesViewModel
) {
    Column() {

        HomeAppBar(
            title = stringResource(id = R.string.app_name),
            Modifier.height(50.dp)
        )
        ChallengeDetailsView(challengeId, challengesViewModel)
    }
}

@Composable
fun ChallengeDetailsView(
    challengeId: String,
    challengesViewModel: ChallengesViewModel
) {

    val state = challengesViewModel.challengeDetailState.collectAsState()

    challengesViewModel.getChallengeDetails(challengeId)

    LazyColumn {
        if (state.value.progress){
            item { LoadingItem() }
        }else if (!state.value.progress && state.value.error.isNotEmpty()){
            item { ErrorDialog(message = state.value.error) }
        } else {
            item { DetailContent(state.value)}
        }
    }
}

@Composable
private fun DetailContent(challengeDetailsResponse: ChallengeDetailsResponse) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))

        Name(challengeDetailsResponse)

        DetailProperty(stringResource(R.string.category), challengeDetailsResponse.category )

        DetailProperty(stringResource(R.string.url), challengeDetailsResponse.url, isLink = true)

        DetailProperty(stringResource(R.string.totalAttempts), challengeDetailsResponse.totalAttempts)

        DetailProperty(stringResource(R.string.totalCompleted), challengeDetailsResponse.totalCompleted)

        DetailProperty(stringResource(R.string.totalStars), challengeDetailsResponse.totalStars)

        DetailProperty(stringResource(R.string.voteScore), challengeDetailsResponse.voteScore)

        DetailProperty(stringResource(R.string.description), challengeDetailsResponse.description)

    }
}

@Composable
private fun Name(
    challengeDetailsResponse: ChallengeDetailsResponse
) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Text(
            text = challengeDetailsResponse.name!!,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun DetailProperty(label: String, value: String, isLink: Boolean = false) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider(
            color = Color.Black,
            thickness = 1.dp
        )
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = label,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
        val style = if (isLink) {
            MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.primary)
        } else {
            MaterialTheme.typography.body1
        }
        Text(
            text = value,
            style = style
        )

    }
}

