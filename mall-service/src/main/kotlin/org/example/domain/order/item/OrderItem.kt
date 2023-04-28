package org.example.domain.order.item

import jakarta.persistence.*
import org.example.domain.Base
import org.example.domain.item.Item
import org.example.domain.order.Order

@Entity
@Table(name = "order-items")
class OrderItem() : Base() {

    @ManyToOne
    lateinit var order: Order

    var price: Int = 0

    lateinit var itemToken: String

    var itemId: Long? = null

    lateinit var itemName: String

    @OneToMany(mappedBy = "orderItem", fetch = FetchType.LAZY, cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    lateinit var orderItemOptionList: List<OrderItemOption>

    constructor(order: Order, price: Int, item: Item, itemName: String): this() {
        this.order = order
        this.price = price
        this.itemId = item.id
        this.itemToken = item.itemToken
        this.itemName = itemName
    }

    fun addOrderItemOptionList(orderItemOptionList: List<OrderItemOption>) {
        this.orderItemOptionList = orderItemOptionList
    }

    fun calculatePrice(): Int {
        if(orderItemOptionList.isEmpty()) throw IllegalStateException("가격을 계산할 수 없습니다.")
        return orderItemOptionList.fold(0){
                total, it -> total + (this.price + it.extraPrice) * it.count }
    }
}