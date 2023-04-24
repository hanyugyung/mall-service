package org.example.domain.item

import lombok.Builder

class ItemInfo {

    @Builder
    class GetListOfItem(
        private val itemToken: String
        , private val name: String
        , private val price: Int
    ) {
        companion object {
            fun of(item: Item) : GetListOfItem {
                return GetListOfItem(
                    itemToken = item.itemToken
                    , name = item.name
                    , price = item.price
                )
            }
        }
    }

}