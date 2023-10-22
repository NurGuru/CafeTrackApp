package ru.nurguru.domain.model

class Order(
    val id: Int,
    val guestId: Int,
    val sum: Int
) {

    override fun toString(): String {
        return "$id|$guestId|$sum"
    }
}