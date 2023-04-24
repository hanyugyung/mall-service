package org.example.domain.item

import jakarta.persistence.Entity
import jakarta.persistence.Table
import lombok.NoArgsConstructor
import org.example.domain.Base

@Entity
@Table(name = "items")
@NoArgsConstructor
class Item() : Base() {
    lateinit var itemToken: String private set
    lateinit var name: String private set
    var price: Int = 0
    var isActive: Boolean = true

    var partnerId: Long? = null

    constructor(name: String, price: Int, partnerId: Long): this() {
        this.itemToken = System.currentTimeMillis().toString()
        this.name = name
        this.price = price
        this.partnerId = partnerId
    }
}