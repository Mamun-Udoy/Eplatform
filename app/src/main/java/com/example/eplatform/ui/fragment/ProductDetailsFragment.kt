package com.example.eplatform.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eplatform.R
import com.example.eplatform.databinding.FragmentProductDetailsBinding
import com.example.eplatform.network.model.ProductResponse
import com.google.gson.Gson
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class ProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding

    private lateinit var dataItem: ProductResponse.ProductResItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {

            val dataStr = arguments?.getString("data_item")
            dataItem = Gson().fromJson(dataStr, ProductResponse.ProductResItem::class.java)
            Log.d("check_item", "check item value $dataStr")

        }

        val carousel: ImageCarousel = binding.carousel
        carousel.registerLifecycle(lifecycle)
        val list = mutableListOf<CarouselItem>()

        for (item in dataItem.images!!) {
            list.add(
                CarouselItem(
                    imageUrl = item,
                )
            )
        }
        carousel.setData(list)
        binding.discount.text = "Discount: 0 %"
        binding.brand.text ="Brand: empty "
        binding.categories.text ="Categories: null "
        binding.stock.text ="Stock: null "
        binding.rating.text ="Rating: null "

    }


}