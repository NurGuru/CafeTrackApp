package ru.nurguru.domain.model

/**
 * Discount - модель скидки гостя
 * @value NONE отстутсвие скидки пока посещений меньше 10
 * @value FIRST_DISCOUNT,//постоянная скидка 5% для гостей с более чем 10 посещений (т.е. гостей со статусом GOOD )
 * @value SECOND_DISCOUNT;//постоянная скидка для гостей потративших более 50000(т.е. гостей со статусом VIP )

 */

enum class Discount {
    NONE,
    FIRST_DISCOUNT,
    SECOND_DISCOUNT;

    companion object {
        fun fromString(discount: String): Discount = Discount.values().find { it.name == discount } ?: NONE
    }
}