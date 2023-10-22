package ru.nurguru.domain.model

/**
 * Product - модель продукта
 * @param productImg- изображение товара
 * @param productName-  имя товара
 * @param category- категория товара
 * @param price- цена товара
 * @param primeCost- себестоимость товара
 */
data class Product(
    val productId: Int,
    val productImg: String,
    val productName: String,
    val category: String,
    val price: Int,
    val primeCost: Int,

    ) {
    override fun toString(): String {
        return "$productId|$productImg|$productName|$category|$price|$primeCost"
    }
}