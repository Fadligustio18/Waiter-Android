package com.example.firshapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.firshapp.R
import com.example.firshapp.adapter.PopularAdapter
import com.example.firshapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.images_promo, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.images_promo3, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.images_promo2, ScaleTypes.FIT))


        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList, ScaleTypes.FIT)
        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun doubleClick(position: Int) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(position: Int) {
                val itemPosition = imageList[position]
                val item = "Selected Image $position"
                Toast.makeText(requireContext(), item, Toast.LENGTH_SHORT).show()
            }
        })
        val foodName = listOf("Hod dog", "Hod dong", "Burger","Pizza","Pizza")
        val Price = listOf("Rp.15.000", "Rp.10.000", "Rp.20.000", "Rp.12.000", "Rp.18.000")
        val popularFoodImages = listOf(
            R.drawable.produk,
            R.drawable.produk1,
            R.drawable.produk2,
            R.drawable.produk5,
            R.drawable.produk4
        )
        val adapter = PopularAdapter(foodName, Price, popularFoodImages)
        val popularRecyclerView = view.findViewById<RecyclerView>(R.id.popularRecyclerView)
        popularRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        popularRecyclerView.adapter = adapter

    }

}