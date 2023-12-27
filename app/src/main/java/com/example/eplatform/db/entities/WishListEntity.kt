package com.example.eplatform.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlistitem")
data class WishListEntity(
    @PrimaryKey(autoGenerate = false) val id: Int?,
    val description: String?,
    val price: Int?,
    val title: String?,
    val categoryId: Int?,
    val image: String?,
    val name: String?
)
