package com.example.mycodingchallenge.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.mycodingchallenge.viewmodel.ChallengesViewModel
import com.example.mycodingchallenge.ui.theme.MyCodingChallengeTheme
import com.example.mycodingchallenge.ui.views.ListingScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val challengesViewModel: ChallengesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MyCodingChallengeTheme {
                ListingScreen(this@MainActivity, challengesViewModel)
            }

        }
    }
}

