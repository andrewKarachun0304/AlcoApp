package com.andrewkarachun0304.alcoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CocktailAdapter(val listener: Listener) : RecyclerView.Adapter<CocktailAdapter.CocktailVH>() {

    private var cocktailList = ArrayList<Cocktail>()

    fun updateList(list: List<Cocktail>) {
        cocktailList = list as ArrayList<Cocktail>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailVH {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.cocktail_tem, parent, false)
        return CocktailVH(view)
    }

    override fun onBindViewHolder(holder: CocktailVH, position: Int) {
        holder.bind(cocktailList[position])
    }

    override fun getItemCount() = cocktailList.size

    inner class CocktailVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var nameTv: TextView
        private var typeTv: TextView
        private var categoryTv: TextView
        private var imageIv: ImageView

        init {
            with(itemView) {
                nameTv = findViewById(R.id.cocktail_name_tv)
                typeTv = findViewById(R.id.cocktail_type_tv)
                categoryTv = findViewById(R.id.cocktail_category_tv)
                imageIv = findViewById(R.id.cocktail_image_iv)

                setOnClickListener {
                    listener.onClick(cocktailList[layoutPosition])
                }
            }
        }

        fun bind(cocktail: Cocktail) {
            nameTv.text = cocktail.name
            typeTv.text = cocktail.type
            categoryTv.text = cocktail.category
            Picasso.get().load(cocktail.imageUrl).into(imageIv)
        }
    }

    interface Listener {
        fun onClick(cocktail: Cocktail)
    }
}