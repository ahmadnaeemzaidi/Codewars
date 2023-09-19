package com.example.mycodingchallenge.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.mycodingchallenge.ui.theme.MyCodingChallengeTheme
import com.example.mycodingchallenge.ui.views.ChallengeDetailsScreen
import com.example.mycodingchallenge.ui.views.ListingScreen
import com.example.mycodingchallenge.viewmodel.ChallengesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChallengeDetailsActivity: ComponentActivity() {

    private val challengeId: String by lazy {
        intent?.getSerializableExtra(CHALLENGE_ID) as String
    }

    private val challengesViewModel: ChallengesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MyCodingChallengeTheme {
                ChallengeDetailsScreen(challengeId, challengesViewModel)
            }

        }
    }



    companion object {
        private const val CHALLENGE_ID = "challenge_id"
        fun newIntent(context: Context, challengeId: String) =
            Intent(context, ChallengeDetailsActivity::class.java).apply {
                putExtra(CHALLENGE_ID, challengeId)
            }
    }
}