package ru.nurguru.data.local.db

import android.content.ContentValues
import ru.nurguru.data.safeCallReadable
import ru.nurguru.data.safeCallWritable
import ru.nurguru.domain.model.Category

class CategoryLocalDataSource(private val database: Database) {

    fun addCategory(category: Category) {
        database.safeCallWritable {
            val values = ContentValues().apply {
                put(CategoryTable.Columns.CATEGORY_IMG, category.categoryImg)
                put(CategoryTable.Columns.CATEGORY_NAME, category.categoryName)

            }
            it.insert(CategoryTable.tableName, null, values)
        }
    }

    fun updateCategory(category: Category, id: Int) {
        database.safeCallWritable {
            val values = ContentValues().apply {
                put(CategoryTable.Columns.CATEGORY_IMG, category.categoryImg)
                put(CategoryTable.Columns.CATEGORY_NAME, category.categoryName)

            }
            it.update(
                CategoryTable.tableName,
                values,
                "${CategoryTable.Columns.CATEGORY_ID} = ?",
                arrayOf(id.toString())

            )
        }
    }

    fun deleteCategory(id: Int) {
        database.writableDatabase.apply {
            delete(
                CategoryTable.tableName,
                "${CategoryTable.Columns.CATEGORY_ID} = ?",
                arrayOf(id.toString())
            )
        }
    }


    fun getCategories(): List<Category> {
        val categoryList = mutableListOf<Category>()

        database.safeCallReadable {
            it.query(
                CategoryTable.tableName,
                null,
                null,
                null,
                null,
                null,
                null,
            ).use { cursor ->
                while (cursor.moveToNext()) {

                    with(cursor) {
                        val category = Category(
                            categoryId  = getInt(getColumnIndexOrThrow(CategoryTable.Columns.CATEGORY_ID)),
                            categoryImg = getString(getColumnIndexOrThrow(CategoryTable.Columns.CATEGORY_IMG)),
                            categoryName = getString(getColumnIndexOrThrow(CategoryTable.Columns.CATEGORY_NAME))
                        )
                        categoryList.add(category)
                    }
                }
            }
        }
        return categoryList
    }

    fun getCategoryByName(name: String): Category? = getCategories().find { it.categoryName == name }

    fun getProductById(id: Int): Category? = getCategories().find { it.categoryId == id }

}