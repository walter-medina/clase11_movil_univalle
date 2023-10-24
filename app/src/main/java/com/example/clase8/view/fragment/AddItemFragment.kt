package com.example.clase8.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.clase8.R
import com.example.clase8.databinding.FragmentAddItemBinding
import com.example.clase8.model.Inventory
import com.example.clase8.model.Product
import com.example.clase8.viewmodel.InventoryViewModel

class AddItemFragment : Fragment() {

    private lateinit var binding: FragmentAddItemBinding
    private val inventoryViewModel: InventoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddItemBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controladores()
        observerViewModel()


    }

    private fun controladores() {
        validarDatos()
        binding.btnSaveInventory.setOnClickListener {
            saveInvetory()
        }
    }

    private fun saveInvetory(){
        val name = binding.etName.text.toString()
        val price = binding.etPrice.text.toString().toInt()
        val quantity = binding.etQuantity.text.toString().toInt()
        val inventory = Inventory(name = name, price = price, quantity = quantity)
        inventoryViewModel.saveInventory(inventory)
        Log.d("test",inventory.toString())
        Toast.makeText(context,"Artículo guardado !!", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()

    }

    private fun validarDatos() {
        val listEditText = listOf(binding.etName, binding.etPrice, binding.etQuantity)

        for (editText in listEditText) {
            editText.addTextChangedListener {
                val isListFull = listEditText.all{
                    it.text.isNotEmpty() // si toda la lista no está vacía
                }
                binding.btnSaveInventory.isEnabled = isListFull
            }
        }
    }



    private fun observerViewModel(){
        observerListProduct()
    }

    private fun observerListProduct() {

        inventoryViewModel.getProducts()
        inventoryViewModel.listProducts.observe(viewLifecycleOwner){ lista ->

            val product = lista[2]
            Glide.with(binding.root.context).load(product.image).into(binding.ivImagenApi)
            binding.tvTitleProduct.text = product.title
        }
    }


}