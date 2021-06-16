package com.fdhasna21.multimedia.Activity7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fdhasna21.multimedia.R
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Activity7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Weather"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }



    fun getServerAPI() : Retrofit?{
        //todo : q, units, mode
        var BASE_URL : String = "https://community-open-weather-map.p.rapidapi.com/weather?q=Jakarta%2CID"
        var retrofit : Retrofit? = null
        var httpClient = OkHttpClient.Builder()

        if(retrofit == null){
            httpClient.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                    val original: Request = chain.request()

                    val request = original.newBuilder()
                        .addHeader("x-rapidapi-key", getString(R.string.rapid_api_key))
                        .addHeader("x-rapidapi-host", getString(R.string.rapid_header))
                        .header("Accept", "application/json")
                        .method(original.method, original.body)
                        .build()
                    return chain.proceed(request)
                }
            })

            val gson = GsonBuilder()
                .setLenient()
                .create()

            val client = httpClient.build()
            retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit
    }
}