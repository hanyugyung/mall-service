package org.example.domain.order.address

import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.example.domain.Base
import org.example.domain.user.address.Address

@Entity
@Table(name = "order-addresses")
class OrderAddress : Base() {

    @OneToOne
    lateinit var address: Address

    lateinit var receiverContact: String

    lateinit var receiverName: String

    lateinit var receiveMemo: String
}