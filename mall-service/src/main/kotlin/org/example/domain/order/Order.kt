package org.example.domain.order

import jakarta.persistence.*
import lombok.Getter
import org.example.domain.Base
import org.example.domain.item.Item
import org.example.domain.order.address.OrderAddress
import org.example.domain.order.item.OrderItem

@Entity
@Table(name = "orders")
class Order() : Base() {

    lateinit var orderToken: String

    var totalPrice: Int = 0

    private var userId: Long? = null

    @Enumerated(EnumType.STRING)
    var status: Status = Status.PAYED // TODO 결제는 논외

    @Getter
    enum class Status {
        PAYED, PREPARING, DELIVERING, COMPLETE
    }

    @OneToOne(cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    lateinit var orderAddress: OrderAddress

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    lateinit var orderItemList: List<OrderItem>

    constructor(userId: Long, orderAddress: OrderAddress): this() {
        this.orderToken = System.currentTimeMillis().toString()
        this.userId = userId
        this.orderAddress = orderAddress
    }

    fun addOrderItemList(orderItemList: List<OrderItem>) {
        this.orderItemList = orderItemList
        calculateTotalPrice()
    }

    private fun calculateTotalPrice() {
        if(orderItemList.isEmpty()) throw IllegalStateException("가격을 계산할 수 없습니다.")
        this.totalPrice = orderItemList.fold(0) {
                total, it -> total + it.calculatePrice() }
    }
}