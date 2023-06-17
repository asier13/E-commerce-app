package com.example.proyecto_extraordinaria.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.proyecto_extraordinaria.modulos.Product
import com.example.proyecto_extraordinaria.databinding.FragmentDetalleBinding

class DetalleFragment : DialogFragment() {
    private var _binding: FragmentDetalleBinding? = null
    private lateinit var product: Product
    private val binding get() = _binding!!

    companion object {
        fun newInstance(prod: Product) : DetalleFragment {
            var f = DetalleFragment()
            val b = Bundle()
            b.putSerializable("product", prod)
            f.arguments = b
            return f
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        product = arguments?.getSerializable("product") as Product
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetalleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.productName.text = product.title
        binding.productDescription.text = product.description
        binding.productStock.text = "Stock: " + product.stock.toString()
        binding.productPrice.text = product.price.toString() + "$"

        Glide.with(requireContext()).load(product.image).into(binding.imageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}