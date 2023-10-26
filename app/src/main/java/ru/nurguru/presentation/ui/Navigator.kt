package ru.nurguru.presentation.ui

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.nurguru.R
import java.lang.ref.WeakReference

object Navigator {

    fun toAdminScreen(manager: WeakReference<FragmentManager>,
    adminFragment: AdminFragment)
            = goToScreen(
        manager = manager,
        fragment = adminFragment,
        screenId = R.id.mainPlaceHolder,
    )

    fun toCategoryScreen(
        manager: WeakReference<FragmentManager>,
        categoryFragment: CategoryFragment
    ) = goToScreen(
        manager = manager,
        fragment = categoryFragment,
        screenId = R.id.adminPlaceHolder,
    )

    fun toGuestsScreen(
        manager: WeakReference<FragmentManager>,
        guestFragment: GuestFragment
    ) = goToScreen(
        manager = manager,
        fragment = guestFragment,
        screenId = R.id.adminPlaceHolder,
    )

    fun toMenuScreen(
        manager: WeakReference<FragmentManager>,
        menuFragment: MenuFragment
    ) = goToScreen(
        manager = manager,
        fragment = menuFragment,
        screenId = R.id.adminPlaceHolder,
    )

    fun backToMenuScreen(
        manager: WeakReference<FragmentManager>,
        menuFragment: MenuFragment
    ) = goToScreen(
        manager = manager,
        fragment = menuFragment,
        screenId = R.id.productPlaceHolder,
    )

    fun toProductEddScreen(
        manager: WeakReference<FragmentManager>,
        productEddFragment: ProductEddFragment
    ) = goToScreen(
        manager = manager,
        fragment = productEddFragment,
        screenId = R.id.menuPlaceHolder,
    )

    fun toCategoryAddScreen(
        manager: WeakReference<FragmentManager>,
        categoryAddFragment: CategoryAddFragment
    ) = goToScreen(
        manager = manager,
        fragment = categoryAddFragment,
        screenId = R.id.categoryPlaceHolder,
    )

    fun backToCategoryScreen(
        manager: WeakReference<FragmentManager>,
        categoryFragment: CategoryFragment
    ) = goToScreen(
        manager = manager,
        fragment = categoryFragment,
        screenId = R.id.categoryPlaceHolder,
    )

    private fun goToScreen(
        manager: WeakReference<FragmentManager>,
        fragment: Fragment,
        @IdRes screenId: Int
    ) = manager.get()?.run {
        beginTransaction()
            .replace(screenId, fragment)
            .commit()
    }

}