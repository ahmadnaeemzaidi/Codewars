package com.example.mycodingchallenge.ui.views

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.example.mycodingchallenge.ChallengesViewModel
import com.example.mycodingchallenge.LoadingItem
import com.example.mycodingchallenge.R
import com.example.mycodingchallenge.data.response.ChallengeListData
import com.example.mycodingchallenge.utils.ErrorDialog

@Composable
fun ListingScreen(
    context: Context,
    challengesViewModel: ChallengesViewModel
) {
    Column() {

        HomeAppBar(title = stringResource(id = R.string.app_name),
            Modifier.height(50.dp))

        ChallengeListing(context,challengesViewModel)
        
    }
}

@Composable
fun ChallengeListing(
    context: Context,
    challengesViewModel: ChallengesViewModel
) {
    val lazyChallengeItems = challengesViewModel.getAllChallenges().collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.padding(5.dp)) {
        itemsIndexed(lazyChallengeItems) { index, item ->
            item?.let {
                ChallengeItem(challengeItemData = it!!, {

                })
            }
        }
        lazyChallengeItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingItem() }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }

                loadState.refresh is LoadState.Error -> {
                    item {
                        ErrorDialog((loadState.refresh as LoadState.Error).error.message!!)
                    }
                }

                loadState.append is LoadState.Error -> {
                    item {
                        ErrorDialog((loadState.append as LoadState.Error).error.message!!)
                    }
                }
            }
        }
    }
}

@Composable
fun ChallengeItem(challengeItemData: ChallengeListData, onClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .fillMaxWidth()
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            color = Color.White,

            ) {
            (if( challengeItemData.name!= null) challengeItemData.name else "")?.let {
                Text(
                    text = it,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 22.sp),
                    color = Color.Black,
                    modifier = Modifier
                        .padding(start = 30.dp, top = 10.dp, bottom = 10.dp)
                        .fillMaxWidth()
                )
            }
        }
        Divider(color = Color.Black, thickness = 1.dp)

    }

}