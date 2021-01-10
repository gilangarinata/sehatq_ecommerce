package com.gilangarinata.sehatqecommerce.api

import com.gilangarinata.sehatqecommerce.models.EcommerceResponse
import retrofit2.Response
import retrofit2.http.GET


/**
 * Created by Gilang Arinata on 10/01/21.
 * https://github.com/gilangarinata/
 */
interface EcommerceAPI {
    @GET("home")
    suspend fun getHomeData(): Response<EcommerceResponse>
}