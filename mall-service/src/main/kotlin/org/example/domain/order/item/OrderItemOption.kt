package org.example.domain.order.item

import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.example.domain.Base
import org.example.domain.order.Order

@Entity
@Table(name = "order-item-options")
class OrderItemOption() : Base() {

    @ManyToOne
    lateinit var orderItem: OrderItem

    var extraPrice: Int = 0

    lateinit var optionName: String

    constructor(orderItem: OrderItem, extraPrice: Int, optionName: String): this() {
        this.orderItem
        this.extraPrice = extraPrice
        this.optionName = optionName
    }
}