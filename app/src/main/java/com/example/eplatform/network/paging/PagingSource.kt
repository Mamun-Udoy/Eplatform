package com.example.eplatform.network.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.eplatform.network.model.ProductResponse
import com.example.eplatform.repository.AppRepo


const val NETWORK_PAGE_SIZE = 10

class PagingSource(private val appRepo: AppRepo) : PagingSource<Int, ProductResponse.ProductResItem>() {

    override fun getRefreshKey(state: PagingState<Int, ProductResponse.ProductResItem>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductResponse.ProductResItem> {
        try {
            val position = params.key ?: 1
            val offset = if (params.key != null) ((position - 1) * NETWORK_PAGE_SIZE) + 10 else NETWORK_PAGE_SIZE


            // this category was recieving as a parameter
           var category = 1

            Log.d("offset_db", "load: pos: $offset")
            val response = if (category == -1)
                appRepo.getProductItem(offset = offset, limit = 10)
            else appRepo.getCatgoryWiseProduct(category)


            return if (response.isSuccessful) {
                val data = response.body()?: emptyList()
                val prevKey = if (position == 1) null else position - 1
                val nextKey = if (data.isEmpty()) null else position + 1

                LoadResult.Page(
                    data = data,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(Exception("Failed to load data"))
            }

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}