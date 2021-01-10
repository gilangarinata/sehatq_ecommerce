package com.gilangarinata.sehatqecommerce.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gilangarinata.sehatqecommerce.R
import com.gilangarinata.sehatqecommerce.adapters.SearchAdapter
import com.gilangarinata.sehatqecommerce.local.db.entities.SearchDataItem
import com.gilangarinata.sehatqecommerce.models.ProductPromo
import com.gilangarinata.sehatqecommerce.ui.MainActivity
import com.gilangarinata.sehatqecommerce.ui.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*


/**
 * Created by Gilang Arinata on 10/01/21.
 * https://github.com/gilangarinata/
 */

class SearchFragment : Fragment(R.layout.fragment_search) {
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchViewModel: SearchViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        initRecyclerView()
        initProduct()
        initEditText()
    }

    private fun initialize() {
        searchViewModel = (activity as MainActivity).searchViewModel
    }

    private fun initEditText() {
        etSearch.requestFocus()
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                searchViewModel.searchProduct("%${s}%").observe(viewLifecycleOwner, Observer {
                    searchAdapter.differ.submitList(it)
                })
            }
        })
    }

    private fun initProduct() {
        val product1 = SearchDataItem(
            "Nintendo Switch",
            "The Nintendo Switch was released on March 3, 2017 and is",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/7/76/Nintendo-Switch-Console-Docked-wJoyConRB.jpg/430px-Nintendo-Switch-Console-Docked-wJoyConRB.jpg",
            "$6723"
        )

        val product2 = SearchDataItem(
            "PS5 Entertainment System",
            "Released July 15, 1983, the Playstation Entertainment System (PS) is an 8-bit video game",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/NES-Console-Set.jpg/430px-NES-Console-Set.jpg",
            "$6724"
        )

        searchViewModel.upsert(product1)
        searchViewModel.upsert(product2)
    }

    private fun initRecyclerView() {
        searchAdapter = SearchAdapter()
        rvSearch.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        searchAdapter.setOnItemClickListener {
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
                R.id.action_searchFragment_to_detailFragment,
                bundle
            )
        }

    }


}