package ru.nurguru.data.model

class OrderEntity(
    val id: Int,
    val guestId: Int,
    val sum: Int
) {

    override fun toString(): String {
        return "$id|$guestId|$sum"
    }
}