package com.gilangarinata.sehatqecommerce.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gilangarinata.sehatqecommerce.local.db.entities.PurchaseDataItem


/**
 * Created by Gilang Arinata on 28/11/20.
 * https://github.com/gilangarinata/
 */

@Dao
interface PurchaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(purchaseDataItem: PurchaseDataItem)

    @Query("SELECT * FROM purchase_data_item")
    fun getPurchasedItems(): LiveData<List<PurchaseDataItem>>
}