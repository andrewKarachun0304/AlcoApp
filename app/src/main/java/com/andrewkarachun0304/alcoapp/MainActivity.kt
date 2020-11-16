package com.andrewkarachun0304.alcoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andrewkarachun0304.alcoapp.mappers.CocktailMapper
import com.andrewkarachun0304.alcoapp.retrofit.CocktailRetrofitFactory
import kotlinx.coroutines.*
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private val cocktailAdapter by lazy {
        CocktailAdapter(object : CocktailAdapter.Listener {
            override fun onClick(cocktail: Cocktail) {
                Toast.makeText(this@MainActivity, cocktail.name, Toast.LENGTH_SHORT).show()
            }
        })
    }
    private val retrofit by lazy {
        CocktailRetrofitFactory.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.cocktails_rv)

        recyclerView.apply {
            adapter = cocktailAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        CoroutineScope(Dispatchers.IO).launch {
            val response =
                retrofit.getCocktailsByNameAsync("margarita").await()
            if (response.isSuccessful) {
                val cocktailList = response.body()?.drinks?.map {
                    CocktailMapper.map(it)
                } ?: emptyList()
                withContext(Dispatchers.Main){
                    cocktailAdapter.updateList(cocktailList)
                }
            }
        }
    }
}