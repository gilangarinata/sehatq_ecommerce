package com.gilangarinata.sehatqecommerce.repository

import com.gilangarinata.sehatqecommerce.api.RetrofitInstance
import com.gilangarinata.sehatqecommerce.local.db.PurchaseDatabase
import com.gilangarinata.sehatqecommerce.local.db.entities.PurchaseDataItem


/**
 * Created by Gilang Arinata on 10/01/21.
 * https://github.com/gilangarinata/
 */
class HomeRepository(
    private val db: PurchaseDatabase
) {
    suspend fun getHomeData() = RetrofitInstance.api.getHomeData()
    suspend fun upsert(item: PurchaseDataItem) = db.getPurchaseDao().upsert(item)
    fun getAllPurchased() = db.getPurchaseDao().getPurchasedItems()
}