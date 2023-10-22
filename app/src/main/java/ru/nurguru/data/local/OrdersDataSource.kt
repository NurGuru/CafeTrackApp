package ru.nurguru.data.local

import ru.nurguru.data.model.OrderEntity

interface OrdersDataSource {
    fun getOrdersList(): List<OrderEntity>

    fun saveOrdersList(newOrdersList: List<OrderEntity>)
}