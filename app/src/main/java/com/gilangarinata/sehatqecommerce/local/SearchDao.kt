package com.gilangarinata.sehatqecommerce.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gilangarinata.sehatqecommerce.local.db.entities.SearchDataItem


/**
 * Created by Gilang Arinata on 28/11/20.
 * https://github.com/gilangarinata/
 */

@Dao
interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(searchDataItem: SearchDataItem)

    @Delete
    suspend fun delete(movieItem: SearchDataItem)

    @Query("SELECT * FROM search_data_item WHERE item_title LIKE :query")
    fun searchProduct(query: String): LiveData<List<SearchDataItem>>
}