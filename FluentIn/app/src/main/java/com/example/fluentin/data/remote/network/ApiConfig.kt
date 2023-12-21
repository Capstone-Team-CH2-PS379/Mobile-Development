package com.example.fluentin.data.remote.network

import android.content.Context
import com.example.fluentin.BuildConfig
import com.example.fluentin.data.AuthenticationToken
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

object ApiConfig {

    private lateinit var apiService: ApiService

    fun getApiService(context: Context): ApiService {
        if (!ApiConfig::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://35.186.151.54:4000/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient(context))
                .build()

            apiService = retrofit.create(ApiService::class.java)
        }
        return apiService
    }

    private fun okhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    level =
                        HttpLoggingInterceptor.Level.BODY
                }
            })
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(AuthenticationToken(context))
            .build()
    }
}
