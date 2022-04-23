package br.com.shiguemori.listadecompras.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.shiguemori.listadecompras.dao.LembretedeComprasRoomDatabase
import br.com.shiguemori.listadecompras.models.Product
import br.com.shiguemori.listadecompras.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val productRepository: ProductRepository
    val products: LiveData<List<Product>>

    init {
        val productDAO = LembretedeComprasRoomDatabase
            .getDatabase(application)
            .productDAO()
        productRepository = ProductRepository(productDAO)
        products = productRepository.products
    }

    fun insert(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        productRepository.insert(product)
    }

    fun delete(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        productRepository.delete(product)
    }

    fun delete(productName: String) = viewModelScope.launch(Dispatchers.IO) {
        productRepository.deleteByProductName(productName)
    }
}