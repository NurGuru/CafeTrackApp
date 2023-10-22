package ru.nurguru.domain

import ru.nurguru.data.model.GuestEntity
import ru.nurguru.domain.model.Guest
import ru.nurguru.domain.model.Status

class GuestsUseCase
    (
    private val repository: GuestsRepository
) {

    fun getGuests() = repository.getGuests()

    fun updateGuest(guest: GuestEntity, id: Int) = repository.updateGuest(guest, id)

    fun addGuest(guest: Guest) = repository.addGuest(guest)

    fun getGuestById(id: Int) = repository.getGuestById(id)

    fun getGuestsList() = repository.getGuestsList()

    fun getGuestListByStatus(status: Status) = repository.getGuestListByStatus(status)

    fun deleteGuest(id: Int) = repository.deleteGuest(id)

}