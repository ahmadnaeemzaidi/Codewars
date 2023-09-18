package com.example.mycodingchallenge.data.exceptions

import java.io.IOException

class ApiException : IOException {
    constructor() : super()
    constructor(
        message: String
    ) : super(
        message
    )
}