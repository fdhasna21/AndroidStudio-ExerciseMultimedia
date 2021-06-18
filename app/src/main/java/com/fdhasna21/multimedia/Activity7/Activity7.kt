package com.fdhasna21.multimedia.Activity7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.fdhasna21.multimedia.R
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_7.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.notify
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.text.SimpleDateFormat
import java.util.*

class Activity7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Weather"

        getServerAPI()!!.create(Activity7Interface::class.java)
            .getWeather("Jakarta", "metric")
            .enqueue(object : Callback<ResponseAPI>{
                override fun onResponse(call: Call<ResponseAPI>, response: Response<ResponseAPI>) {
                    if(response.isSuccessful){
                        val output = response.body()
                        activity7_temp.text = output?.detailInfo?.currentTemp.toString()
                        activity7_city.text = "Jakarta, Indonesia"
                        activity7_desc.text = output?.weather?.get(0)?.main.toString()
                        activity7_sunset.text = "Sunset\n"+convertUnix(output?.detailSys?.sunset!!)
                        activity7_sunrise.text = "Sunrise\n"+convertUnix(output.detailSys.sunrise)

                        Glide.with(this@Activity7)
                            .load("http://openweathermap.org/img/wn/${output.weather.get(0).icon.toString()}@2x.png")
                            .into(activity7_icon)
                    }
                    else{
                        Toast.makeText(this@Activity7, response.errorBody().toString(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseAPI>, t: Throwable) {
                    Toast.makeText(this@Activity7, t.toString(), Toast.LENGTH_SHORT).show()
                    Log.d("failure", t.toString())
                }
            })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun convertUnix(unix_Timestamp : Long) : String{
        val date = Date(unix_Timestamp.times(1000))
        val sdf = SimpleDateFormat("hh:mm a\nz")
        return sdf.format(date)
    }

    private fun getServerAPI() : Retrofit?{
        //todo : q, units, mode
        var BASE_URL : String = "https://community-open-weather-map.p.rapidapi.com/"
        var retrofit : Retrofit? = null
        var httpClient = OkHttpClient.Builder()

        if(retrofit == null){
            httpClient.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                    val original: Request = chain.request()

                    val request = original.newBuilder()
                        .header("x-rapidapi-key", getString(R.string.rapid_api_key))
                        .header("x-rapidapi-host", getString(R.string.rapid_header))
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