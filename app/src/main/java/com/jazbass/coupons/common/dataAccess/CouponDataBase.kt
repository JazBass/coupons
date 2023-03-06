package com.jazbass.coupons.common.dataAccess

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jazbass.coupons.common.entities.CouponEntity

@Database(entities = [CouponEntity::class], version = 1)
abstract class CouponDataBase: RoomDatabase() {

    abstract fun couponDao(): CouponDao

}