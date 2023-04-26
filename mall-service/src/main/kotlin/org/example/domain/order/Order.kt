package org.example.domain.order

import jakarta.persistence.*
import lombok.Getter
import org.example.domain.Base
import org.example.domain.order.address.OrderAddress
import org.example.domain.order.item.OrderItem

@Entity
@Table(name = "orders")
class Order() : Base() {

    lateinit var itemToken: String private set

    val userId: Long? = null

    @Enumerated(EnumType.STRING)
    var status: Status = Status.PAYED // TODO 결제는 논외

    @Getter
    enum class Status {
        PAYED, PREPARING, DELIVERING, COMPLETE
    }

//    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.PERSIST])
    @OneToOne
    lateinit var orderAddress: OrderAddress

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.PERSIST])
    lateinit var orderItemList: List<OrderItem>
}