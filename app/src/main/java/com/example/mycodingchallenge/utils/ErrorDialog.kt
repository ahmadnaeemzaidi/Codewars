package com.example.mycodingchallenge.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun ErrorDialog(
    message: String
) {
    MaterialTheme {
        Column {
            val openDialog = remember { mutableStateOf(true)  }

            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(text = "Error")
                    },
                    text = {
                        Text(message)
                    },
                    confirmButton = {
                        Button(

                            onClick = {
                                openDialog.value = false
                            }) {
                            Text("Ok")
                        }
                    },
                )
            }

        }

    }
}