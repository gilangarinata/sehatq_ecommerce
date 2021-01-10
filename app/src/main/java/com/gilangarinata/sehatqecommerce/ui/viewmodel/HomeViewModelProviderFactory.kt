package com.gilangarinata.sehatqecommerce.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gilangarinata.sehatqecommerce.repository.HomeRepository


/**
 * Created by Gilang Arinata on 10/01/21.
 * https://github.com/gilangarinata/
 */
class HomeViewModelProviderFactory(
    private val homeRepository: HomeRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(homeRepository) as T
    }
}