package org.example.infrastructure.order

import org.example.domain.order.Order
import org.example.domain.order.OrderRepository
import org.example.domain.order.item.OrderItem
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryImpl constructor(
    val orderRepository: OrderJpaRepository
): OrderRepository{

    override fun store(order: Order) {
        orderRepository.save(order)
    }

    override fun findAllBy(userToken: String): List<Order> {
        return orderRepository.findAllByUserToken(userToken)
    }

    override fun findBy(userToken: String, orderToken: String): Order? {
        return orderRepository.findByUserTokenAndOrderToken(userToken, orderToken)
    }
}