package ru.nurguru.data.repository

import ru.nurguru.data.local.OrdersDataSource
import ru.nurguru.domain.OrdersRepository
import ru.nurguru.domain.model.Order
import map

class OrdersRepositoryImpl(private val dataSource: OrdersDataSource) : OrdersRepository {
    override fun getOrders(): List<Order> = dataSource.getOrdersList().map { it.map() }

    override fun getOrderId(id: Int): Order? = getOrders().find { it.id == id }

    override fun saveGuests(orders: List<Order>) {
        dataSource.saveOrdersList(orders.map { it.map() })
    }
}
