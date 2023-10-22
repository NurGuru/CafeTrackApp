package ru.nurguru.domain.model

/**
 * Guest - модель гостя
 * @param id- айдишник гостя
 * @param name-  имя гостя
 * @param number- номер телефона
 * @param visits- оличество посещений
 * @param moneySpent- потрачено денег в завдении
 * @param status- статус лоялности гостя
 * @param discount- скидка гостя
 */
data class Guest(
    val id: Int,
    val name: String,
    val number: Long,
    val visits: Int,
    val moneySpent: Int,
    val status: Status,
    val discount: Discount,
) {

    override fun toString(): String {
        return "$id|$name|$number|$visits|$moneySpent|$status|$discount"
    }
}