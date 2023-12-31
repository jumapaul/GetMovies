package com.example.getmoview.data.remote
import com.example.getmoview.BuildConfig.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val url = original.url.newBuilder().addQueryParameter("api_key", API_KEY).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}