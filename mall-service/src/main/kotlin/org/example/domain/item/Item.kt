package org.example.domain.item

import jakarta.persistence.*
import lombok.Getter
import lombok.NoArgsConstructor
import org.example.domain.Base
import org.example.domain.item.option.ItemOption
import org.example.domain.user.address.Address

@Entity
@Table(name = "items")
@NoArgsConstructor
class Item() : Base() {
    lateinit var itemToken: String private set
    lateinit var name: String private set
    var price: Int = 0

    @Enumerated(EnumType.STRING)
    var status: Status = Status.VISIBLE

    @Getter
    enum class Status {
        VISIBLE, INVISIBLE
    }

    var partnerToken: String? = null

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.PERSIST])
    var itemOptionList: List<ItemOption> = listOf()

    constructor(name: String, price: Int, partnerToken: String): this() {
        this.itemToken = System.currentTimeMillis().toString()
        this.name = name
        this.price = price
        this.partnerToken = partnerToken
    }

    fun addItemOptionList(itemOptionList: List<ItemOption>) {
        this.itemOptionList = itemOptionList
    }
}