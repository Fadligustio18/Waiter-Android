package com.example.firshapp.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firshapp.adapter.CartAdapter
import com.example.firshapp.api.ApiClient
import com.example.firshapp.databinding.FragmentCartBinding
import com.example.firshapp.model.DeleteItem
import com.example.firshapp.model.Item
import com.example.firshapp.model.PostItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchItems()

        binding.btnSubmit.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val harga = binding.etHarga.text.toString().toIntOrNull()
            val deskripsi = binding.etDeskripsi.text.toString().trim()
            val isAvailable = binding.swIsAvailable.isChecked

            if (name.isNotEmpty() && harga != null && deskripsi.isNotEmpty()) {
                val newItem = PostItem(name, harga, isAvailable, deskripsi)
                createItem(newItem)
            } else {
                Toast.makeText(requireContext(), "Harap isi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchItems() {
        ApiClient.instance.getItems().enqueue(object : Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                if (response.isSuccessful) {
                    val items = response.body()
                    if (items != null) {
                        setupRecyclerView(items)
                    }
                } else {
                    Toast.makeText(requireContext(), "Failed to fetch items", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                Log.e("CartFragment", "Failed to fetch items", t)
                Toast.makeText(requireContext(), "An error occurred", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerView(items: List<Item>) {
        val adapter = CartAdapter(items) { item ->
            deleteItem(item)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun createItem(item: PostItem) {
        ApiClient.instance.createItem(item)
            .enqueue(object : Callback<Item> {
                override fun onResponse(
                    call: Call<Item>,
                    response: Response<Item>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            requireContext(),
                            "Item berhasil ditambahkan",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.etName.text.clear()
                        binding.etHarga.text.clear()
                        binding.etDeskripsi.text.clear()
                        binding.swIsAvailable.isChecked = false
                        fetchItems() // Refresh the list
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Log.e("CartFragment", "Gagal menambahkan item. Kode: ${response.code()}, Pesan: $errorBody")
                        Toast.makeText(
                            requireContext(),
                            "Gagal menambahkan item: ${response.code()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Item>, t: Throwable) {
                    Log.e("CartFragment", "onFailure: ${t.message}", t)
                    Toast.makeText(
                        requireContext(),
                        "Terjadi kesalahan: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    private fun deleteItem(item: Item) {
        ApiClient.instance.deleteItem(DeleteItem(item.id)).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    Toast.makeText(requireContext(), "Item berhasil dihapus", Toast.LENGTH_SHORT).show()
                    fetchItems() // Refresh the list
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("CartFragment", "Gagal menghapus item. Kode: ${response.code()}, Pesan: $errorBody")
                    Toast.makeText(requireContext(), "Gagal menghapus item: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.e("CartFragment", "onFailure: ${t.message}", t)
                Toast.makeText(requireContext(), "Terjadi kesalahan: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}