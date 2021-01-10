package com.gilangarinata.sehatqecommerce.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gilangarinata.sehatqecommerce.R
import com.gilangarinata.sehatqecommerce.adapters.CategoryAdapter
import com.gilangarinata.sehatqecommerce.adapters.ProductAdapter
import com.gilangarinata.sehatqecommerce.ui.MainActivity
import com.gilangarinata.sehatqecommerce.ui.viewmodel.HomeViewModel
import com.gilangarinata.sehatqecommerce.utils.Resource
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * Created by Gilang Arinata on 10/01/21.
 * https://github.com/gilangarinata/
 */
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var viewModel: HomeViewModel
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productAdapter: ProductAdapter

    val TAG = "HomeFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        setupRecyclerView()
    }

    private fun initComponents() {
        viewModel = (activity as MainActivity).viewModel
        viewModel.homeData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { homeResponse ->
                        categoryAdapter.differ.submitList(homeResponse[0].data.category)
                        productAdapter.differ.submitList(homeResponse[0].data.productPromo)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, message)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        cvSearch.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        categoryAdapter = CategoryAdapter()
        productAdapter = ProductAdapter()
        rvCategory.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

        rvProduct.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        productAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("product", it)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_detailFragment,
                bundle
            )
        }


    }

}