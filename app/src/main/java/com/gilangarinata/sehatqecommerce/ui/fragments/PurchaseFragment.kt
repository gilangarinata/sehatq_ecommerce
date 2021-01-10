package com.gilangarinata.sehatqecommerce.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gilangarinata.sehatqecommerce.R
import com.gilangarinata.sehatqecommerce.adapters.PurchaseAdapter
import com.gilangarinata.sehatqecommerce.models.ProductPromo
import com.gilangarinata.sehatqecommerce.ui.MainActivity
import com.gilangarinata.sehatqecommerce.ui.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_purchase.*


/**
 * Created by Gilang Arinata on 10/01/21.
 * https://github.com/gilangarinata/
 */
class PurchaseFragment : Fragment(R.layout.fragment_purchase) {

    private lateinit var viewModel: HomeViewModel
    private lateinit var purchaseAdapter: PurchaseAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        initComponents()
    }

    private fun setupRecyclerView() {
        purchaseAdapter = PurchaseAdapter()
        rvPurchase.apply {
            adapter = purchaseAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        purchaseAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                val product = ProductPromo(
                    it.description,
                    "",
                    it.imageUrl,
                    0,
                    it.orice,
                    it.title
                )
                putSerializable("product", product)
            }
            findNavController().navigate(
                R.id.action_purchaseFragment2_to_detailFragment,
                bundle
            )
        }
    }


    private fun initComponents() {
        viewModel = (activity as MainActivity).viewModel
        viewModel.getAllPurchaseItem().observe(viewLifecycleOwner, Observer {
            purchaseAdapter.differ.submitList(it)
        })
    }
}