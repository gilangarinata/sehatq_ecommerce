package com.gilangarinata.sehatqecommerce.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gilangarinata.sehatqecommerce.repository.SearchRepository


/**
 * Created by Gilang Arinata on 29/11/20.
 * https://github.com/gilangarinata/
 */

@Suppress("UNCHECKED_CAST")
class SearchViewModelProviderFactory(
    private val repository: SearchRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(repository) as T
    }

}