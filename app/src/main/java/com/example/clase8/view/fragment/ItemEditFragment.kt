package com.example.clase8.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.clase8.R
import com.example.clase8.databinding.FragmentItemEditBinding
import com.example.clase8.model.Inventory
import com.example.clase8.viewmodel.InventoryViewModel


class ItemEditFragment : Fragment() {
    private lateinit var binding: FragmentItemEditBinding
    private val inventoryViewModel: InventoryViewModel by viewModels()
    private lateinit var receivedInventory: Inventory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemEditBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInventory()
        controladores()

    }

    private fun controladores(){
        binding.btnEdit.setOnClickListener {
            updateInventory()
        }
    }

    private fun dataInventory(){
        val receivedBundle = arguments
        receivedInventory = receivedBundle?.getSerializable("dataInventory") as Inventory
        binding.etName.setText(receivedInventory.name)
        binding.etPrice.setText(receivedInventory.price.toString())
        binding.etQuantity.setText(receivedInventory.quantity.toString())

    }

    private fun updateInventory(){
        val name = binding.etName.text.toString()
        val price = binding.etPrice.text.toString().toInt()
        val quantity = binding.etQuantity.text.toString().toInt()
        val inventory = Inventory(receivedInventory.id, name,price,quantity)
        inventoryViewModel.updateInventory(inventory)
        findNavController().navigate(R.id.action_itemEditFragment_to_homeInventoryFragment)

    }
}