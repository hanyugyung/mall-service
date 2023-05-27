package org.example.domain.order

import org.example.domain.order.item.OrderItem

interface OrderRepository {
    fun store(order: Order)

    fun findAllBy(userToken: String): List<Order>

    fun findBy(userToken: String, orderToken: String): Order?
}