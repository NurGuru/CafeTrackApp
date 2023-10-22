package ru.nurguru.domain.model

/**
 * Status - модель статуса гостя
 * @value NEW- непостоянные гости, у которых меньше 10 посещений
 * @value GOOD- постоянные гости у которых больше 10 посещений
 * @value VIP- вип гости, те кто потратил больше 50000 в заведении
 */
enum class Status {
    NEW,
    GOOD,
    VIP;

    companion object {
        fun fromString(status: String): Status = values().find { it.name == status } ?: NEW
    }
}
