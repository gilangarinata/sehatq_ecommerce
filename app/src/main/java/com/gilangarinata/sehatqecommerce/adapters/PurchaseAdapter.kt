package com.gilangarinata.sehatqecommerce.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gilangarinata.sehatqecommerce.R
import com.gilangarinata.sehatqecommerce.local.db.entities.PurchaseDataItem
import kotlinx.android.synthetic.main.item_search.view.*


/**
 * Created by Gilang Arinata on 10/01/21.
 * https://github.com/gilangarinata/
 */
class PurchaseAdapter : RecyclerView.Adapter<PurchaseAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<PurchaseDataItem>() {
        override fun areItemsTheSame(
            oldItem: PurchaseDataItem,
            newItem: PurchaseDataItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PurchaseDataItem,
            newItem: PurchaseDataItem
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_search,
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
            tvTitle.text = item.title
            setOnClickListener {
                onItemClickListener?.let {
                    it(item)
                }
            }
            tvPrice.text = item.orice
            tvDescription.text = item.description
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((PurchaseDataItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (PurchaseDataItem) -> Unit) {
        onItemClickListener = listener
    }
}