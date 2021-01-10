package com.gilangarinata.sehatqecommerce.repository

import com.gilangarinata.sehatqecommerce.local.db.SearchDatabase
import com.gilangarinata.sehatqecommerce.local.db.entities.SearchDataItem


/**
 * Created by Gilang Arinata on 29/11/20.
 * https://github.com/gilangarinata/
 */
class SearchRepository(
    private val db: SearchDatabase
) {
    suspend fun upsert(item: SearchDataItem) = db.getSearchDao().upsert(item)
    fun searchProduct(query: String) = db.getSearchDao().searchProduct(query)
}