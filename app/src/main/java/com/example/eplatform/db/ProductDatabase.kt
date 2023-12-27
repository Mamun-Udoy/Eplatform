package com.example.eplatform.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.eplatform.db.dao.ProductItemDao
import com.example.eplatform.db.entities.ProductEntity


@Database(entities = [ProductEntity::class], version=1)
abstract class ProductDatabase:RoomDatabase() {
    abstract fun productItemDao() : ProductItemDao
}