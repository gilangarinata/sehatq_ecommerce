package com.gilangarinata.sehatqecommerce.local.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Gilang Arinata on 10/01/21.
 * https://github.com/gilangarinata/
 */

@Entity(tableName = "purchase_data_item")
data class PurchaseDataItem(
    @ColumnInfo(name = "item_title")
    var title: String,
    @ColumnInfo(name = "item_description")
    var description: String,
    @ColumnInfo(name = "item_image")
    var imageUrl: String,
    @ColumnInfo(name = "price")
    var orice: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}