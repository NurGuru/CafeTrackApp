package ru.nurguru.domain

import ru.nurguru.data.model.GuestEntity
import ru.nurguru.domain.model.Guest
import ru.nurguru.domain.model.Status

interface GuestsRepository {

    fun getGuests(): List<Guest>

    fun getGuestsList(): List<String>

    fun getGuestById(id: Int): Guest?

    fun getGuestListByStatus(status: Status): List<Guest>

    fun addGuest(guest: Guest)

    fun updateGuest(guest: GuestEntity, id: Int)

    fun deleteGuest(id: Int)



}