package org.example.domain.item

class ItemCommand {

    class RegisterItem(
        private val name: String
        , private val price: Int
        , private val partnerId: Long
    ) {
        fun toEntity(): Item {
            return Item(this.name, this.price, this.partnerId)
        }
    }

}