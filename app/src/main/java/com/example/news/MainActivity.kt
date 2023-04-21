package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.news.retrofit.FactsAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv = findViewById<TextView>(R.id.textView2)
        val btn = findViewById<Button>(R.id.button)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://catfact.ninja/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val factsAPI =  retrofit.create(FactsAPI::class.java)
        btn.setOnClickListener{

            CoroutineScope(Dispatchers.IO).launch {
                val apis = factsAPI.getFact()
                runOnUiThread {
                    tv.text = apis.fact
                }
            }
        }



    }
}