package com.example.proyecto_extraordinaria.adapters

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyecto_extraordinaria.R
import com.example.proyecto_extraordinaria.SecondActivity
import com.example.proyecto_extraordinaria.fragments.CarritoFragment
import com.example.proyecto_extraordinaria.fragments.DetalleFragment
import com.example.proyecto_extraordinaria.modulos.Product

class ProductoAdapter(var context: Context) : RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {
    private var products: ArrayList<Product> = ArrayList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.image)
        var nombreTextView: TextView = itemView.findViewById(R.id.name)
        var precioTextView: TextView = itemView.findViewById(R.id.precio)
        var comprarBoton: Button = itemView.findViewById(R.id.btn_comprar)
        var detalleBoton: Button = itemView.findViewById(R.id.btn_detalle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.layout_fila, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var product = products[position]

        Glide.with(holder.itemView.context).load(product.image).into(holder.imageView)
        holder.nombreTextView.text = product.title
        holder.precioTextView.text = product.price.toString()

        holder.comprarBoton.setOnClickListener {
            CarritoFragment.productos.add(product)
        }

        holder.detalleBoton.setOnClickListener {
            val fm = (context as AppCompatActivity).supportFragmentManager
            val detalle = DetalleFragment.newInstance(product)
            detalle.show(fm, "fragmento_detalle")
        }

    }

    fun addProducto(product: Product) {
        products.add(product)
    }
}