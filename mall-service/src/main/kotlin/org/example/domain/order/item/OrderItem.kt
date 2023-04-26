package org.example.domain.order.item

import jakarta.persistence.*
import org.example.domain.order.Order

@Entity
@Table(name = "order-items")
class OrderItem {

    @ManyToOne
    lateinit var order: Order

    var price: Int = 0

    lateinit var itemToken: String

    var itemId: Long? = null

    lateinit var itemName: String

    @OneToMany(mappedBy = "orderItem", fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.PERSIST])
    lateinit var orderItemOptionList: List<OrderItemOption>
}