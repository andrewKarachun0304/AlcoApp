package com.andrewkarachun0304.alcoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andrewkarachun0304.alcoapp.adapter.CocktailAdapter
import com.andrewkarachun0304.alcoapp.R
import com.andrewkarachun0304.alcoapp.entity.Cocktail
import com.andrewkarachun0304.alcoapp.mappers.CocktailMapper
import com.andrewkarachun0304.alcoapp.retrofit.CocktailRetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchCocktailByNameFragment : Fragment() {
    private val cocktailAdapter by lazy {
        CocktailAdapter(object : CocktailAdapter.Listener {
            override fun onClick(cocktail: Cocktail) {
                Toast.makeText(requireContext(), cocktail.name, Toast.LENGTH_SHORT).show()
            }
        })
    }
    private val retrofit by lazy {
        CocktailRetrofitFactory.getInstance()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_cocktail_by_name, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val recyclerView = requireView().findViewById<RecyclerView>(R.id.cocktails_by_name_rv)

        recyclerView.apply {
            adapter = cocktailAdapter
            layoutManager = LinearLayoutManager(requireContext())
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