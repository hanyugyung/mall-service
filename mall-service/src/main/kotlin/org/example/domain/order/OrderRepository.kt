package org.example.domain.order

interface OrderRepository {
    fun save(order: Order): String
}