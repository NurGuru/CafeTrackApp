package ru.nurguru.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.nurguru.data.local.db.GuestsDatabase
import ru.nurguru.data.local.db.GuestLocalDataSource
import ru.nurguru.data.local.db.GuestTable
import ru.nurguru.data.local.db.MenuLocalDataSource
import ru.nurguru.data.local.db.MenuTable
import ru.nurguru.data.repository.GuestsRepositoryImpl
import ru.nurguru.domain.ApplyDiscountUseCase
import ru.nurguru.domain.DiscountApplierUseCase
import ru.nurguru.domain.GuestsRepository
import ru.nurguru.domain.GuestsUseCase
import ru.nurguru.domain.MenuUseCase


val dataModule = module {
    //зависимости для слоя data

    single { GuestsDatabase(androidContext(), listOf(GuestTable, MenuTable)) }
    singleOf(::GuestLocalDataSource)
    singleOf(::MenuLocalDataSource)
    factoryOf(::GuestsRepositoryImpl) { bind<GuestsRepository>() }


}

val domainModule = module {

    //зависимости для слоя domain
    factoryOf(::ApplyDiscountUseCase)
    factoryOf(::DiscountApplierUseCase)
    factoryOf(::GuestsUseCase)
    factoryOf(::MenuUseCase)

}