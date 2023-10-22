package ru.nurguru.domain.model

class Category(
    val categoryId: Int,
    val categoryImg: String,
    val categoryName: String
) {
    override fun toString(): String {
        return "$categoryId|$categoryImg|$categoryName"
    }
}

