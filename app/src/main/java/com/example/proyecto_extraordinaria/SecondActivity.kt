package com.example.proyecto_extraordinaria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.proyecto_extraordinaria.adapters.ProductoAdapter
import com.example.proyecto_extraordinaria.fragments.CarritoFragment
import com.example.proyecto_extraordinaria.modulos.Product
import org.json.JSONArray

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val adapter = ProductoAdapter(this)
        val url = "https://dummyjson.com/products"
        var peticion: JsonObjectRequest =
            JsonObjectRequest(Request.Method.GET, url, null, {
                val arrayProductos: JSONArray = it.getJSONArray("products")

                for (i in 0 until arrayProductos.length()) {
                    val producto = arrayProductos.getJSONObject(i)
                    val prodduct = Product(producto.getInt("id"),
                        producto.getString("title"),
                        producto.getString("description"),
                        producto.getInt("price"),
                        producto.getDouble("rating"),
                        producto.getInt("stock"),
                        producto.getString("brand"),
                        producto.getJSONArray("images").getString(0)
                    )
                    adapter.addProducto(prodduct)

                    val listado = findViewById<RecyclerView>(R.id.listado)

                    listado.adapter = adapter
                    listado.layoutManager = LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL,false)
                }

            }, {
                Log.v("respuesta", "Error en la peticion")
            })

        Volley.newRequestQueue(this).add(peticion)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.carrito -> {
                val dialog = CarritoFragment()
                dialog.show(supportFragmentManager, "CarritoFragment")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}