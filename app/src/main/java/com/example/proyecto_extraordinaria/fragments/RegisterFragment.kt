package com.example.proyecto_extraordinaria.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.proyecto_extraordinaria.MainActivity
import com.example.proyecto_extraordinaria.databinding.FragmentRegisterBinding
import com.example.proyecto_extraordinaria.SecondActivity
import com.example.proyecto_extraordinaria.modulos.User
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private lateinit var database: FirebaseDatabase

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root

        }
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)


            binding.btnRegistro.setOnClickListener {

                MainActivity.auth.createUserWithEmailAndPassword(binding.editEmail.text.toString(),
                        binding.editPassword.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = MainActivity.auth.currentUser

                            user?.updateProfile(userProfileChangeRequest {
                                displayName = binding.editName.text.toString()
                            })

                            Snackbar.make(binding.root, "Usuario creado correctamente", Snackbar.LENGTH_SHORT).show()

                            val intent = Intent(context, SecondActivity::class.java)
                            startActivity(intent)
                        } else {
                            Snackbar.make(binding.root, "Error al crear el usuario", Snackbar.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

}