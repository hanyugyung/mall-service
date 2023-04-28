package org.example.infrastructure.order

import org.example.domain.order.Order
import org.example.domain.order.OrderRepository
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryImpl constructor(
    val orderRepository: OrderJpaRepository
): OrderRepository{

    override fun save(order: Order): String {
        return orderRepository.save(order).orderToken
    }

}