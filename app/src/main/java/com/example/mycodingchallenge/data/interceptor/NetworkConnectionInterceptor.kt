package com.example.mycodingchallenge.data.interceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.mycodingchallenge.data.exceptions.NoInternetException
import com.example.mycodingchallenge.utils.AppConstants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkConnectionInterceptor (
    context: Context
)
    : Interceptor {

    private val applicationContext = context.applicationContext

    @Throws(NoInternetException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable(applicationContext))
            throw NoInternetException(AppConstants.INTERNET_ERROR)

        val original: Request =
            chain.request().newBuilder().addHeader("action", chain.request().url.toString())
                .build()
        return chain.proceed(original)
    }

    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        }
        return result
    }
}