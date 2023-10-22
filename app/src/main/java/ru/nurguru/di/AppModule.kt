package ru.nurguru.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.nurguru.data.local.db.Database
import ru.nurguru.data.local.db.GuestLocalDataSource
import ru.nurguru.data.local.db.CategoryLocalDataSource
import ru.nurguru.data.local.db.MenuLocalDataSource
import ru.nurguru.data.repository.GuestsRepositoryImpl
import ru.nurguru.domain.ApplyDiscountUseCase
import ru.nurguru.domain.DiscountApplierUseCase
import ru.nurguru.domain.GuestsRepository
import ru.nurguru.domain.GuestsUseCase
import ru.nurguru.domain.MenuUseCase
import ru.nurguru.domain.CategoryUseCase


val dataModule = module {
    //зависимости для слоя data

    single { Database(androidContext()) }
    singleOf(::CategoryLocalDataSource)
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
    factoryOf(::CategoryUseCase)

}