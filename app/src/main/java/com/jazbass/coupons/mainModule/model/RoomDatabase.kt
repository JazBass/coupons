package com.jazbass.coupons.mainModule.model

import android.database.sqlite.SQLiteConstraintException
import com.jazbass.coupons.CouponsApplication
import com.jazbass.coupons.common.dataAccess.CouponDao
import com.jazbass.coupons.common.entities.CouponEntity
import com.jazbass.coupons.common.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDatabase {

    /**Aqui haremos verificaciones*/

    private val dao: CouponDao by lazy { CouponsApplication.dataBase.couponDao() }

    suspend fun consultCouponByCode(code: String) = dao.consultCouponByCode(code)

    suspend fun saveCoupon(couponEntity: CouponEntity) = withContext(Dispatchers.IO){
        try {
            dao.addCoupon(couponEntity)
        }catch (e: Exception){
            (e as? SQLiteConstraintException)?.let { throw Exception(Constants.ERROR_EXIST) }
            throw Exception(e.message ?: Constants.ERROR_UNKNOWN)
        }
    }

}