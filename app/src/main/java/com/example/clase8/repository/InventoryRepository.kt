package com.example.clase8.repository
import android.content.Context
import android.widget.Toast
import com.example.clase8.data.InventoryDB
import com.example.clase8.data.InventoryDao
import com.example.clase8.model.Inventory
import com.example.clase8.model.Product
import com.example.clase8.webservice.ApiService
import com.example.clase8.webservice.ApiUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InventoryRepository  @Inject constructor(
    @ApplicationContext val context: Context,
    private val inventoryDao: InventoryDao,
    private val apiService: ApiService
){
     suspend fun saveInventory(inventory:Inventory){
         withContext(Dispatchers.IO){
             inventoryDao.saveInventory(inventory)
         }
     }

    suspend fun getListInventory():MutableList<Inventory>{
        return withContext(Dispatchers.IO){
            inventoryDao.getListInventory()
        }
    }

    suspend fun deleteInventory(inventory: Inventory){
        withContext(Dispatchers.IO){
            inventoryDao.deleteInventory(inventory)
        }
    }

    suspend fun updateRepositoy(inventory: Inventory){
        withContext(Dispatchers.IO){
            inventoryDao.updateInventory(inventory)
        }
    }

    suspend fun getProducts(): MutableList<Product> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getProducts()
                response
            } catch (e: Exception) {

                e.printStackTrace()
                mutableListOf()
            }
        }
    }
}