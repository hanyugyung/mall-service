package org.example.infrastructure.order;

import org.example.domain.order.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderJpaRepository: JpaRepository<Order, Long> {

    fun findByOrderToken(orderToken: String): Order?

    fun findAllByUserToken(userToken: String): List<Order>

    fun findByUserTokenAndOrderToken(userToken: String, orderToken: String): Order?
}
