package ru.nurguru.data.repository

import ru.nurguru.domain.model.Guest
import ru.nurguru.domain.model.Status
import ru.nurguru.domain.GuestsRepository
import map
import ru.nurguru.data.local.db.GuestLocalDataSource
import ru.nurguru.data.model.GuestEntity

class GuestsRepositoryImpl(
    private val localDataSource: GuestLocalDataSource,
) : GuestsRepository {

    override fun getGuests(): List<Guest> = localDataSource.getGuests().map { it.map() }

    override fun getGuestsList(): List<String> = getGuests().map { "$it\n" }

    override fun getGuestById(id: Int): Guest? = getGuests().find { it.id == id }

    override fun getGuestListByStatus(status: Status): List<Guest> =
        getGuests().filter { it.status == status }

    override fun addGuest(guest: Guest) {
        localDataSource.addGuest(
            GuestEntity(
                id = guest.id,
                name = guest.name,
                number = guest.number,
                visits = guest.visits,
                moneySpent = guest.moneySpent,
                statusStr = guest.status.toString(),
                discountStr = guest.discount.toString()
            )
        )
    }

    override fun updateGuest(guest: GuestEntity, id: Int) {
        localDataSource.updateGuest(guest, id)
    }

    override fun deleteGuest(id: Int) {
        localDataSource.deleteGuest(id)
    }

}

