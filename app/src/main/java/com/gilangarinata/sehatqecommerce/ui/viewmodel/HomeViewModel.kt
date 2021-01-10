package com.gilangarinata.sehatqecommerce.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilangarinata.sehatqecommerce.local.db.entities.PurchaseDataItem
import com.gilangarinata.sehatqecommerce.models.EcommerceResponse
import com.gilangarinata.sehatqecommerce.repository.HomeRepository
import com.gilangarinata.sehatqecommerce.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response


/**
 * Created by Gilang Arinata on 10/01/21.
 * https://github.com/gilangarinata/
 */
class HomeViewModel(
    val homeRepository: HomeRepository
) : ViewModel() {

    val homeData: MutableLiveData<Resource<EcommerceResponse>> = MutableLiveData()

    init {
        getHomeData()
    }

    fun upsert(item: PurchaseDataItem) = CoroutineScope(Dispatchers.Main).launch {
        homeRepository.upsert(item)
    }

    fun getAllPurchaseItem() = homeRepository.getAllPurchased()

    fun getHomeData() = viewModelScope.launch {
        homeData.postValue(Resource.Loading())
        val response = homeRepository.getHomeData()
        homeData.postValue(handleHomeDataResponse(response))
    }

    private fun handleHomeDataResponse(response: Response<EcommerceResponse>): Resource<EcommerceResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}