package com.example.eplatform.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.eplatform.db.dao.ProductItemDao
import com.example.eplatform.db.dao.WishListItemDao
import com.example.eplatform.db.entities.ProductEntity
import com.example.eplatform.db.entities.WishListEntity


@Database(entities = [ProductEntity::class, WishListEntity::class], version=2)
abstract class ProductDatabase:RoomDatabase() {
    abstract fun productItemDao() : ProductItemDao

    abstract fun WishListItemDao() : WishListItemDao
}