package com.gilangarinata.sehatqecommerce.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.gilangarinata.sehatqecommerce.local.db.entities.SearchDataItem
import com.gilangarinata.sehatqecommerce.repository.SearchRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Created by Gilang Arinata on 29/11/20.
 * https://github.com/gilangarinata/
 */

class SearchViewModel(
    private val repository: SearchRepository
) : ViewModel() {
    fun upsert(item: SearchDataItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun searchProduct(query: String) = repository.searchProduct(query)
}