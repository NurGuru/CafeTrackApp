package ru.nurguru.data.model

/**
 * Guest - модель гостя
 * @param id- айдишник гостя
 * @param name-  имя гостя
 * @param number- номер телефона
 * @param visits- оличество посещений
 * @param moneySpent- потрачено денег в завдении
 * @param statusStr- статус лоялности гостя
 * @param discountStr- скидка гостя
 */
data class GuestEntity(
    var id: Int,
    val name: String,
    val number: Long,
    val visits: Int,
    val moneySpent: Int,
    val statusStr: String,
    val discountStr: String,
) {

    override fun toString(): String {
        return "$id|$name|$number|$visits|$moneySpent|$statusStr|$discountStr"
    }
}