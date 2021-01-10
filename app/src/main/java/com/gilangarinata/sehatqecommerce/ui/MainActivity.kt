package com.gilangarinata.sehatqecommerce.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.gilangarinata.sehatqecommerce.R
import com.gilangarinata.sehatqecommerce.local.db.PurchaseDatabase
import com.gilangarinata.sehatqecommerce.local.db.SearchDatabase
import com.gilangarinata.sehatqecommerce.repository.HomeRepository
import com.gilangarinata.sehatqecommerce.repository.SearchRepository
import com.gilangarinata.sehatqecommerce.ui.viewmodel.HomeViewModel
import com.gilangarinata.sehatqecommerce.ui.viewmodel.HomeViewModelProviderFactory
import com.gilangarinata.sehatqecommerce.ui.viewmodel.SearchViewModel
import com.gilangarinata.sehatqecommerce.ui.viewmodel.SearchViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: HomeViewModel
    lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initialize()
    }

    private fun initialize() {
        val purchaseDatabase = PurchaseDatabase(this)
        val repository = HomeRepository(purchaseDatabase)
        val factory = HomeViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        val searchDatabase = SearchDatabase(this)
        val searchRepository = SearchRepository(searchDatabase)
        val searchFactory = SearchViewModelProviderFactory(searchRepository)
        searchViewModel = ViewModelProvider(this, searchFactory).get(SearchViewModel::class.java)
    }

    private fun initView() {
        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())
        val navController = navHostFragment.findNavController()
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> showBottomNav()
                R.id.purchaseFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        bottomNavigationView.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        bottomNavigationView.visibility = View.GONE
    }
}