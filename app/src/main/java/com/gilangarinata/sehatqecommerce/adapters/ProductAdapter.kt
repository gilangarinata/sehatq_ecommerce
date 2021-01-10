package com.gilangarinata.sehatqecommerce.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gilangarinata.sehatqecommerce.R
import com.gilangarinata.sehatqecommerce.models.ProductPromo
import kotlinx.android.synthetic.main.item_product.view.*


/**
 * Created by Gilang Arinata on 10/01/21.
 * https://github.com/gilangarinata/
 */
class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<ProductPromo>() {
        override fun areItemsTheSame(oldItem: ProductPromo, newItem: ProductPromo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductPromo, newItem: ProductPromo): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_product,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(item.imageUrl)
                .into(ivProduct)
            tvProductTitle.text = item.title
            setOnClickListener {
                onItemClickListener?.let {
                    it(item)
                }
            }

            var isFavorited = item.loved == 1

            if (isFavorited) {
                ivFavorite.setColorFilter(
                    ContextCompat.getColor(context, R.color.red_400),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
            } else {
                ivFavorite.setColorFilter(
                    ContextCompat.getColor(context, R.color.grey_20),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
            }

            ivFavorite.setOnClickListener {
                isFavorited = if (!isFavorited) {
                    ivFavorite.setColorFilter(
                        ContextCompat.getColor(context, R.color.red_400),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
                    true
                } else {
                    ivFavorite.setColorFilter(
                        ContextCompat.getColor(context, R.color.grey_20),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
                    false
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((ProductPromo) -> Unit)? = null

    fun setOnItemClickListener(listener: (ProductPromo) -> Unit) {
        onItemClickListener = listener
    }
}