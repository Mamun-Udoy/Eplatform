package com.example.eplatform.network.model


import com.google.gson.annotations.SerializedName

class ProductResponse : ArrayList<ProductResponse.ProductResItem>() {
    data class ProductResItem(
        @SerializedName("category")
        val category: Category?,
        @SerializedName("creationAt")
        val creationAt: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("images")
        val images: List<String?>,
        @SerializedName("price")
        val price: Int?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("updatedAt")
        val updatedAt: String?
    ) {
        data class Category(
            @SerializedName("creationAt")
            val creationAt: String?,
            @SerializedName("id")
            val id: Int?,
            @SerializedName("image")
            val image: String?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("updatedAt")
            val updatedAt: String?
        )
    }
}