package com.example.proyecto_extraordinaria.modulos

class Product(var p_id: Int?=null, var title: String?=null, var description: String?=null, var price: Int?=null, var rating: Double?=null, var stock: Int?=null, var brand: String?=null, var image: String?=null) : java.io.Serializable {
    override fun toString(): String {
        return "Product(id=$p_id, title=$title, description=$description, price=$price, rating=$rating, stock=$stock, brand=$brand, image=$image)"
    }
}