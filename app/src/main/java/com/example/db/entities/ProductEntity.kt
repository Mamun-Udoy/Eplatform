package com.example.db.entities


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "productitem")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false) val id: Int?,
    val description: String?,
    val price: Int?,
    val title: String?,
    val categoryId: Int?,
    val image: String?,
    val name: String?

)