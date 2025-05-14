package com.example.clase8.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.clase8.model.Inventory
import com.example.clase8.model.Product
import com.example.clase8.repository.InventoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class InventoryViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule() //código que involucra LiveData y ViewModel

    lateinit var inventoryViewModel: InventoryViewModel

    @Mock
    lateinit var inventoryRepository: InventoryRepository
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        inventoryViewModel = InventoryViewModel(inventoryRepository)
    }

    @Test
    fun `test método totalProducto`(){
        //Sacar las comillas invertidas en windows alt + 96
        //given (qué necesitamos:condiciones previas necesarias para que la prueba se ejecute correctamente)
        val price = 10
        val quantity = 5
        val expectedResult = (price * quantity).toDouble()

        //when (Aquí, ejecutas el código o la función que estás evaluando.)
        val resul = inventoryViewModel.totalProducto(price, quantity)

        //Then (lo que tiene que pasar:resultados esperados )
        assertEquals(expectedResult, resul,0.0)
    }

    @Test
    fun `test método getProducts`() = runBlocking {
        //given
        // es responsable de ejecutar tareas en el hilo principal, necesitamos simular ese proceso
        Dispatchers.setMain(UnconfinedTestDispatcher())
        // Configurar el comportamiento del repositorio simulado
        val mockProducts = mutableListOf(
            Product(0, "zapatos", "hola como estas")
        )
        `when`(inventoryRepository.getProducts()).thenReturn(mockProducts)

        // Llamar a la función que queremos probar
        //when
        inventoryViewModel.getProducts()

        // Asegurarse de que la LiveData de productos se haya actualizado correctamente
        //then
        assertEquals(inventoryViewModel.listProducts.value, mockProducts)
    }

    @Test
    fun testSaveInventory_success() = runBlocking {
        //given
        Dispatchers.setMain(UnconfinedTestDispatcher())
        val inventory= Inventory(id = 1, name = "Item1", price = 10, quantity = 5)
        // Llamamos al método que queremos probar
        inventoryViewModel.saveInventory(inventory)

        // Verificamos que en verdad se está consumiendo
        // la instrucción inventoryRepository.saveInventory(inventory) en el método testeado
        verify(inventoryRepository).saveInventory(inventory)
    }
}