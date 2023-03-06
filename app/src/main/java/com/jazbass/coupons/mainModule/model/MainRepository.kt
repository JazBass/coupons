package com.jazbass.coupons.mainModule.model

import com.jazbass.coupons.common.entities.CouponEntity
import com.jazbass.coupons.common.utils.Constants
import com.jazbass.coupons.common.utils.validateTextCode


/**Abstraer el repositorio del SDK de Android y otras librerias**/
class MainRepository {

    val roomDatabase = RoomDatabase()

    suspend fun consultCouponByCode(code: String) = if (validateTextCode(code)) {
        roomDatabase.consultCouponByCode(code)
    } else {
        throw Exception(Constants.ERROR_LENGTH)
    }


    suspend fun saveCoupon(couponEntity: CouponEntity) {
        if (validateTextCode(couponEntity.code)) {
            roomDatabase.saveCoupon(couponEntity)
        } else {
            throw Exception(Constants.ERROR_LENGTH)
        }
    }
}