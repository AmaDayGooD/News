package com.example.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.news.databinding.ActivityMainBinding
import com.example.news.retrofit.FactsAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private  var launcher : ActivityResultLauncher<Intent>? = null
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
            if(result.resultCode == RESULT_OK)
            {
                val text = result.data?.getStringExtra("key1")
            }
        }
        val retrofit = Retrofit.Builder()
            .baseUrl("https://harry-potter-api-en.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val factsAPI =  retrofit.create(FactsAPI::class.java)

        binding.randbtn.setOnClickListener {

            val randomNumber = Random.nextInt(73)
            binding.edtext.setText(randomNumber.toString())

                CoroutineScope(Dispatchers.IO).launch {
                    val apis = factsAPI.getFact(randomNumber.toString().toInt())
                    runOnUiThread {
                        binding.textView2.text = apis.spell
                    }
                }
        }
            binding.button3.setOnClickListener{
            if(binding.edtext.text.isNotEmpty() && (binding.edtext.text.toString().toInt() in (1..72)))
            {
            CoroutineScope(Dispatchers.IO).launch {
                val apis = factsAPI.getFact(binding.edtext.text.toString().toInt())
                runOnUiThread {
                    binding.textView2.text = apis.spell
                }
            }
        }
            else binding.textView2.text = "Enter another number!"
        }




    }
}