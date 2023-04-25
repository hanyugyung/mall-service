package org.example.domain.item.option

import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import lombok.NoArgsConstructor
import org.example.domain.Base
import org.example.domain.item.Item

@Entity
@Table(name = "item-options")
@NoArgsConstructor
class ItemOption() : Base() {
    var stock: Int = 0
    var extraPrice: Int = 0
    lateinit var optionName: String

    @ManyToOne
    lateinit var item: Item

    constructor(stock: Int, extraPrice: Int, optionName: String, item: Item) : this() {
        this.stock = stock
        this.extraPrice = extraPrice
        this.optionName = optionName
        this.item = item
    }
}