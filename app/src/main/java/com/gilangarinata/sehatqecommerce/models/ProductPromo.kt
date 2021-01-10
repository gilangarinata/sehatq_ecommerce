package com.gilangarinata.sehatqecommerce.models

import java.io.Serializable

data class ProductPromo(
    val description: String,
    val id: String,
    val imageUrl: String,
    val loved: Int,
    val price: String,
    val title: String
) : Serializable