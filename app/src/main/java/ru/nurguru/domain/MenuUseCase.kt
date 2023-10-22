package ru.nurguru.domain

import ru.nurguru.data.local.db.MenuLocalDataSource
import ru.nurguru.domain.model.Product

class MenuUseCase(private val dataSource: MenuLocalDataSource
) {
    fun addProduct(product: Product) = dataSource.addProduct(product)
    fun getProducts() = dataSource.getProducts()
    fun getProductByName(name:String) = dataSource.getProductByName(name)
    fun updateProduct(product: Product, id:Int) = dataSource.updateProduct(product, id)
    fun deleteProduct( id:Int) = dataSource.deleteProduct( id)
    fun getProductById(id: Int) = dataSource.getProductById(id)

}