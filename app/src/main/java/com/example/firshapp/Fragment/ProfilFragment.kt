package com.example.firshapp.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.firshapp.R
import com.example.firshapp.login

class ProfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnGoLogin = view.findViewById<Button>(R.id.btnGoLogin)
        val btnGoSignUp = view.findViewById<Button>(R.id.btnGoSignUp)

        btnGoLogin.setOnClickListener {
            val intent = Intent(requireContext(), login::class.java)
            startActivity(intent)
        }

        btnGoSignUp.setOnClickListener {
            // Untuk sementara tampilkan pesan karena halaman SignUp belum dibuat
            Toast.makeText(requireContext(), "Halaman Sign Up akan segera hadir", Toast.LENGTH_SHORT).show()
        }
    }
}
