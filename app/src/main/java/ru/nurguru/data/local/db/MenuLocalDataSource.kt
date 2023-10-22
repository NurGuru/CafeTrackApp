package ru.nurguru.data.local.db

import android.content.ContentValues
import ru.nurguru.domain.model.Product
import ru.nurguru.data.safeCallReadable
import ru.nurguru.data.safeCallWritable

class MenuLocalDataSource(private val database: Database) {

    fun addProduct(product: Product) {
        database.safeCallWritable {
            val values = ContentValues().apply {
                put(MenuTable.Columns.PRODUCT_IMG, product.productImg)
                put(MenuTable.Columns.PRODUCT_NAME, product.productName)
                put(MenuTable.Columns.CATEGORY, product.category)
                put(MenuTable.Columns.PRICE, product.price)
                put(MenuTable.Columns.PRIME_COST, product.primeCost)
            }
            it.insert(MenuTable.tableName, null, values)
        }
    }

    fun updateProduct(product: Product, id: Int) {
        database.safeCallWritable {
            val values = ContentValues().apply {
                put(MenuTable.Columns.PRODUCT_IMG, product.productImg)
                put(MenuTable.Columns.PRODUCT_NAME, product.productName)
                put(MenuTable.Columns.CATEGORY, product.category)
                put(MenuTable.Columns.PRICE, product.price)
                put(MenuTable.Columns.PRIME_COST, product.primeCost)
            }
            it.update(
                MenuTable.tableName,
                values,
                "${MenuTable.Columns.PRODUCT_ID} = ?",
                arrayOf(id.toString())

            )
        }
    }

    fun deleteProduct(id: Int) {
        database.writableDatabase.apply {
            delete(
                MenuTable.tableName,
                "${MenuTable.Columns.PRODUCT_ID} = ?",
                arrayOf(id.toString())
            )
        }
    }


    fun getProducts(): List<Product> {
        val productList = mutableListOf<Product>()

        database.safeCallReadable {
            it.query(
                MenuTable.tableName,
                null,
                null,
                null,
                null,
                null,
                null,
            ).use { cursor ->
                while (cursor.moveToNext()) {

                    with(cursor) {
                        val product = Product(
                            productId = getInt(getColumnIndexOrThrow(MenuTable.Columns.PRODUCT_ID)),
                            productImg = getString(getColumnIndexOrThrow(MenuTable.Columns.PRODUCT_IMG)),
                            productName = getString(getColumnIndexOrThrow(MenuTable.Columns.PRODUCT_NAME)),
                            category = getString(getColumnIndexOrThrow(MenuTable.Columns.CATEGORY)),
                            price = getInt(getColumnIndexOrThrow(MenuTable.Columns.PRICE)),
                            primeCost = getInt(getColumnIndexOrThrow(MenuTable.Columns.PRIME_COST))
                        )
                        productList.add(product)
                    }
                }
            }
        }
        return productList
    }

    fun getProductByName(name: String): Product? = getProducts().find { it.productName == name }

    fun getProductById(id: Int): Product? = getProducts().find { it.productId == id }

}