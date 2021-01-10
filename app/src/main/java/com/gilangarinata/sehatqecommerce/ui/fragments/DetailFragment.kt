package com.gilangarinata.sehatqecommerce.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.gilangarinata.sehatqecommerce.R
import com.gilangarinata.sehatqecommerce.local.db.entities.PurchaseDataItem
import com.gilangarinata.sehatqecommerce.ui.MainActivity
import com.gilangarinata.sehatqecommerce.ui.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_detail.*


/**
 * Created by Gilang Arinata on 10/01/21.
 * https://github.com/gilangarinata/
 */
class DetailFragment : Fragment(R.layout.fragment_detail) {
    val args: DetailFragmentArgs by navArgs()
    private lateinit var viewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupViews()
        initToolbar()
    }

    private fun initialize() {
        viewModel = (activity as MainActivity).viewModel
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
    }

    private fun setupViews() {
        val product = args.product
        Glide.with(this)
            .load(product.imageUrl)
            .into(ivProduct)
        tvTitle.text = product.title
        tvPrice.text = product.price
        tvDescription.text = product.description

        var isFavorited = false
        ivFavorite.setOnClickListener {
            isFavorited = if (!isFavorited) {
                ivFavorite.setColorFilter(
                    ContextCompat.getColor(
                        activity!!.applicationContext,
                        R.color.red_400
                    ), android.graphics.PorterDuff.Mode.SRC_IN
                )
                true
            } else {
                ivFavorite.setColorFilter(
                    ContextCompat.getColor(
                        activity!!.applicationContext,
                        R.color.grey_20
                    ), android.graphics.PorterDuff.Mode.SRC_IN
                )
                false
            }
        }

        ivShare.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val shareBody = product.imageUrl
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, product.title)
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }

        btnBuyNow.setOnClickListener {
            val dataItem = PurchaseDataItem(
                product.title,
                product.description,
                product.imageUrl,
                product.price
            )
            viewModel.upsert(
                dataItem
            )
            activity!!.onBackPressed()
        }
    }
}