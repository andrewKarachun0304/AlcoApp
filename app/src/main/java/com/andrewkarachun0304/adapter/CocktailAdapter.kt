package com.andrewkarachun0304.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.andrewkarachun0304.alcoapp.R
import com.andrewkarachun0304.alcoapp.entity.Cocktail
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
        private var starIb: ImageButton

        init {
            with(itemView) {
                nameTv = findViewById(R.id.cocktail_name_tv)
                typeTv = findViewById(R.id.cocktail_type_tv)
                categoryTv = findViewById(R.id.cocktail_category_tv)
                imageIv = findViewById(R.id.cocktail_image_iv)
                starIb = findViewById(R.id.star_ib)

                setOnClickListener {
                    listener.onClick(cocktailList[layoutPosition])
                }

                starIb.setOnClickListener {
                    if (!cocktailList[layoutPosition].state) {
                        starIb.setImageDrawable(
                            getDrawable(
                                resources,
                                R.drawable.ic_gold_star,
                                resources.newTheme()
                            )
                        )
                        cocktailList[layoutPosition].state = true
                    } else {
                        starIb.setImageDrawable(
                            getDrawable(
                                resources,
                                R.drawable.ic_star_outline,
                                resources.newTheme()
                            )
                        )
                        cocktailList[layoutPosition].state = false
                    }
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