package ru.nurguru.domain

import map
import ru.nurguru.domain.model.Guest

/**
 * [apply] - функция применяющая скидку
 */
class ApplyDiscountUseCase(
    private val discountApplier: DiscountApplierUseCase,
    private val repository: GuestsRepository

) {
    fun apply(guest: Guest): List<Guest> {
        var newGuest = guest
        newGuest = discountApplier.applyDiscountAndChangeStatus(newGuest)

        val newGuestsList = repository.getGuests().map {
            if (it.id == newGuest.id) newGuest else it
        }

        repository.updateGuest(newGuest.map(), newGuest.id)
        return newGuestsList
    }
}
