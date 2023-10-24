package com.example.clase8.viewmodel

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.clase8.model.Inventory
import com.example.clase8.model.Product
import com.example.clase8.repository.InventoryRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class InventoryViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var inventoryViewModel: InventoryViewModel
    private lateinit var inventoryRepository: InventoryRepository


    @Before
    fun setUp() {
        inventoryRepository = mock(InventoryRepository::class.java)
        inventoryViewModel = mock(InventoryViewModel::class.java)

    }

    @Test
    fun `getProducts should update LiveData`() = testDispatcher.runBlockingTest {
        // Configurar el comportamiento del repositorio simulado
        val mockProducts = mutableListOf(
            Product(0,"zapatos","hola como estas")
        )
        `when`(inventoryRepository.getProducts()).thenReturn(mockProducts)

        // Llamar a la función que queremos probar
        inventoryViewModel.getProducts()


        // Asegurarse de que la LiveData de productos se haya actualizado correctamente
       assert(inventoryViewModel.listProducts.value == mockProducts)
    }
}