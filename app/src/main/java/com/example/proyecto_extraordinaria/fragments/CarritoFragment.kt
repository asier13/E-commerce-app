package com.example.proyecto_extraordinaria.fragments


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.proyecto_extraordinaria.adapters.CarritoAdapter
import com.example.proyecto_extraordinaria.modulos.Product
import com.example.proyecto_extraordinaria.databinding.FragmentCarritoBinding
import com.google.android.material.snackbar.Snackbar


class CarritoFragment : DialogFragment() {
    private var _binding: FragmentCarritoBinding? = null
    private val binding get() = _binding!!

    private lateinit var carritoAdapter: CarritoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCarritoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carritoAdapter = CarritoAdapter(requireContext(), productos)

        binding.carrito.adapter = carritoAdapter
        binding.carrito.layoutManager = LinearLayoutManager(context)

        binding.finalizarCompraBtn.setOnClickListener {
            // Calcular el total del carrito
            var total = 0.0
            for (product in productos) {
                total += product.price!!
            }

            // Crear un diálogo de confirmación
            AlertDialog.Builder(context)
                .setTitle("Confirmar compra")
                .setMessage("¿Estás seguro de que quieres confirmar la compra? Total: ${total}€")
                .setPositiveButton("Sí") { dialog, _ ->
                    // Limpiar el carrito y notificar al adaptador
                    productos.clear()
                    carritoAdapter.notifyDataSetChanged()

                    // Mostrar un Snackbar con un mensaje de éxito
                    Snackbar.make(binding.root, "Compra realizada con éxito con un total de ${total}€", Snackbar.LENGTH_SHORT).show()

                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
    companion object {
        val productos = ArrayList<Product>()
    }
}