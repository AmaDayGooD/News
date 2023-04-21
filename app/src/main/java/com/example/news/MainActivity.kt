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
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv = findViewById<TextView>(R.id.textView2)
        val btn = findViewById<Button>(R.id.button3)
        val enrt = findViewById<TextView>(R.id.editTextNumber2)
        val rnd = findViewById<Button>(R.id.rand)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://harry-potter-api-en.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val factsAPI =  retrofit.create(FactsAPI::class.java)

        rnd.setOnClickListener {

            val randomNumber = Random.nextInt(73)
            enrt.setText(randomNumber.toString())


                CoroutineScope(Dispatchers.IO).launch {
                    val apis = factsAPI.getFact(randomNumber.toString().toInt())
                    runOnUiThread {
                        tv.text = apis.spell
                    }
                }
        }


        btn.setOnClickListener{
            if(enrt.text.isNotEmpty() && (enrt.text.toString().toInt() in (1..72)))
            {
            CoroutineScope(Dispatchers.IO).launch {
                val apis = factsAPI.getFact(enrt.text.toString().toInt())
                runOnUiThread {
                    tv.text = apis.spell
                }
            }
        }
            else tv.text = "Enter another number!"
        }




    }
}