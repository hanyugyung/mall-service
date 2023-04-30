package org.example.domain.order

interface OrderRepository {
    fun store(order: Order)
}