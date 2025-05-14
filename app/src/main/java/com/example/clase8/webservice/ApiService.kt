package com.example.clase8.webservice

import com.example.clase8.model.Product
import com.example.clase8.utils.Constants.END_POINT
import retrofit2.http.GET
import javax.inject.Inject

interface ApiService {
    @GET(END_POINT)
    suspend fun getProducts(): MutableList<Product>
}