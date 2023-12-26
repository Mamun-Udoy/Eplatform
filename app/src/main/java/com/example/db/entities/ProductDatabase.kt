package com.example.db.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.db.dao.ProductItemDao


@Database(entities = [ProductEntity::class], version=1)
abstract class ProductDatabase:RoomDatabase() {
    abstract fun productItemDao() : ProductItemDao
}