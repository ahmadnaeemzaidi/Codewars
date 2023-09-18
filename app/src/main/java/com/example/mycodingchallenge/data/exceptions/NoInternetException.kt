package com.example.mycodingchallenge.data.exceptions

import java.io.IOException

open class NoInternetException : IOException {
    constructor() : super()
    constructor(
        message: String,
        ) : super(
        message
    )
}