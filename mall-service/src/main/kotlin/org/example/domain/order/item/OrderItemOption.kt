package org.example.domain.order.item

import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.example.domain.Base

@Entity
@Table(name = "order-item-options")
class OrderItemOption : Base() {

    @ManyToOne
    lateinit var orderItem: OrderItem

}