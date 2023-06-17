package com.example.proyecto_extraordinaria.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_extraordinaria.R
import com.example.proyecto_extraordinaria.fragments.CarritoFragment
import com.example.proyecto_extraordinaria.modulos.Product

class CarritoAdapter(private val context: Context, private val productos: List<Product>) : RecyclerView.Adapter<CarritoAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nombreProducto: TextView = itemView.findViewById(R.id.nombre_carrito)
        var precioProducto: TextView = itemView.findViewById(R.id.precio_carrito)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.carrito_fila, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productos[position]

        holder.nombreProducto.text = product.title
        holder.precioProducto.text = "${product.price}€"
    }
}