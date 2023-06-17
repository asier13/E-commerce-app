package com.example.proyecto_extraordinaria.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.proyecto_extraordinaria.MainActivity
import com.example.proyecto_extraordinaria.SecondActivity
import com.example.proyecto_extraordinaria.databinding.FragmentLogInBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.btnEntrar.setOnClickListener {
            MainActivity.auth.signInWithEmailAndPassword(
                binding.editEmail.text.toString(),
                binding.editPassword.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful){
                    var intent = Intent(activity, SecondActivity::class.java)
                    startActivity(intent)
                } else {
                    Snackbar.make(binding.root, "Fallo al logear", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}