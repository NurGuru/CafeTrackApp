package ru.nurguru.domain

import ru.nurguru.data.local.db.CategoryLocalDataSource
import ru.nurguru.domain.model.Category

class CategoryUseCase(private val dataSource: CategoryLocalDataSource) {

    fun addCategory(category: Category) = dataSource.addCategory(category)

    fun getCategories() = dataSource.getCategories()

    fun getCategoryByName(name: String) = dataSource.getCategoryByName(name)

    fun updateCategory(category: Category, id: Int) = dataSource.updateCategory(category, id)

    fun deleteCategory(id: Int) = dataSource.deleteCategory(id)

    fun getCategoryById(id: Int) = dataSource.getProductById(id)

}