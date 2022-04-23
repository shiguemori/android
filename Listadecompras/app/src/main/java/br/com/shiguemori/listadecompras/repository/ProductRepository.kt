package br.com.shiguemori.listadecompras.repository

import androidx.lifecycle.LiveData
import br.com.shiguemori.listadecompras.dao.ProductDAO
import br.com.shiguemori.listadecompras.models.Product

class ProductRepository(private val productDAO: ProductDAO) {
    val products: LiveData<List<Product>> = productDAO.getProducts()
    suspend fun insert(product: Product) {
        productDAO.insert(product)
    }

    suspend fun deleteAll() {
        productDAO.deleteAll()
    }

    suspend fun delete(product: Product) {
        productDAO.delete(product)
    }

    suspend fun deleteByProductName(productName: String) {
        productDAO.deleteByProductName(productName)
    }

}