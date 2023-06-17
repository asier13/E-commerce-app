package com.example.proyecto_extraordinaria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import com.example.proyecto_extraordinaria.databinding.ActivityMainBinding
import com.example.proyecto_extraordinaria.fragments.LogInFragment
import com.example.proyecto_extraordinaria.fragments.RegisterFragment
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        val btnLogin : Button = findViewById(R.id.btnLogin)
        val btnRegistro : Button = findViewById(R.id.btnRegistro)

        btnLogin.setOnClickListener{
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmento.id, LogInFragment())
                .commit()
        }
        btnRegistro.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmento.id, RegisterFragment())
                .commit()
        }

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmento.id, LogInFragment())
            .commit()
    }

    companion object {
        lateinit var auth: FirebaseAuth
    }
}