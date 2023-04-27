package org.example.domain.order.address

import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.example.domain.Base

@Entity
@Table(name = "order-addresses")
class OrderAddress() : Base() {

    lateinit var address1: String private set
    lateinit var address2: String private set
    lateinit var zipCode: String private set

    lateinit var receiverContact: String

    lateinit var receiverName: String

    lateinit var receiveMemo: String

    constructor(
        address1: String,
        address2: String,
        zipCode: String,
        receiverContact: String,
        receiverName: String,
        receiveMemo: String
    ) : this() {
        this.address1 = address1
        this.address2 = address2
        this.zipCode = zipCode
        this.receiverContact = receiverContact
        this.receiverName = receiverName
        this.receiveMemo = receiveMemo
    }
}