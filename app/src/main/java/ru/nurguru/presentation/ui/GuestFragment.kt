package ru.nurguru.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import map
import org.koin.android.ext.android.inject
import ru.nurguru.data.model.GuestEntity
import ru.nurguru.databinding.GuestFragmentBinding
import ru.nurguru.domain.ApplyDiscountUseCase
import ru.nurguru.domain.GuestsUseCase
import ru.nurguru.domain.model.Status

class GuestFragment : Fragment() {
    private lateinit var binding: GuestFragmentBinding
    private val guestUseCase: GuestsUseCase by inject()
    private val applyDiscountUseCase: ApplyDiscountUseCase by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GuestFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            createGuestButton.setOnClickListener {
                createContainer.visibility = View.VISIBLE
            }

            //создать гостя СДЕЛАЛ
            saveGuest.setOnClickListener {
                guestUseCase.addGuest(
                    GuestEntity(
                        guestUseCase.getGuests().size + 1,
                        addName.text.toString(),
                        addNumber.text.toString().toLong(),
                        addVisits.text.toString().toInt(),
                        addMoneySpent.text.toString().toInt(),
                        addStatus.text.toString(),
                        addDiscount.text.toString(),
                    ).map()
                )
            }

            //обновить гостя СДЕЛАЛ

            updateGuest.setOnClickListener {

                guestUseCase.updateGuest(
                    id = addGuestId.text.toString().toInt(),
                    guest = GuestEntity(
                        addGuestId.text.toString().toInt(),
                        addName.text.toString(),
                        addNumber.text.toString().toLong(),
                        addVisits.text.toString().toInt(),
                        addMoneySpent.text.toString().toInt(),
                        addStatus.text.toString(),
                        addDiscount.text.toString(),
                    )
                )
                applyDiscountUseCase.apply(
                    guestUseCase.getGuestById(
                        binding.addGuestId.text.toString().toInt()
                    )!!
                )
            }

            //удалить гостя
            delete.setOnClickListener {
                val dataList = guestUseCase.getGuests()
                val id = addGuestId.text.toString().toInt() - 1
                guestUseCase.deleteGuest(dataList[id].id)
            }
            //пказать данные гостя СДЕЛАЛ
            showGuestData.setOnClickListener {
                createContainer.visibility = View.VISIBLE

                val id = addGuestId.text.toString().toInt()
                val guest = guestUseCase.getGuestById(id)
                guestData.text = ""
                guestData.append(guest.toString())

                addName.setText(guest?.name)
                addNumber.setText(guest?.number.toString())
                addVisits.setText(guest?.visits.toString())
                addMoneySpent.setText(guest?.moneySpent.toString())
                addStatus.setText(guest?.status.toString())
                addDiscount.setText(guest?.discount.toString())
            }

            //показать список всех гостей СДЕЛАЛ НО ПАРСИНГ НЕ ПОЛУЧАЕТСЯ
            showAllGuestButton.setOnClickListener {
                guestData.text = ""
                val guestList = guestUseCase.getGuestsList()
                guestData.append(guestList.toString())
            }
            //показать готсей по статусу СДЕЛАЛ
            showGuestsByStatus.setOnClickListener {
                guestData.text = ""
                val guestListByStatus =
                    guestUseCase.getGuestListByStatus(Status.fromString(binding.addGuestStatus.text.toString()))

                for (item in guestListByStatus) {
                    guestData.append(item.toString())
                    guestData.append("\n")
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = GuestFragment()
    }
}