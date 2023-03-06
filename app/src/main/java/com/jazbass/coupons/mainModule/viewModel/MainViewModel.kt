package com.jazbass.coupons.mainModule.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jazbass.coupons.R
import com.jazbass.coupons.common.entities.CouponEntity
import com.jazbass.coupons.common.utils.getMsgErrorByCode
import com.jazbass.coupons.mainModule.model.MainRepository
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val repository = MainRepository()
    private val result = MutableLiveData<CouponEntity>()

    private val coupon = MutableLiveData<CouponEntity>()

    fun getResult() = result

    private val snackbarMsg = MutableLiveData<Int>()

    fun getSnackbarMsg() = snackbarMsg

    fun consultCouponBybCode(code: String){
        viewModelScope.launch {
            try {
                result.value = repository.consultCouponByCode(code)
            }catch (e: Exception){
                snackbarMsg.value = getMsgErrorByCode(e.message)
            }
        }
    }

    fun saveCoupon(couponEntity: CouponEntity){
        viewModelScope.launch {
            try {
                repository.saveCoupon(couponEntity)
                consultCouponBybCode(couponEntity.code)
                snackbarMsg.value = R.string.created_coupon
            } catch (e: Exception) {
                snackbarMsg.value = getMsgErrorByCode(e.message)
            }
        }
    }
}