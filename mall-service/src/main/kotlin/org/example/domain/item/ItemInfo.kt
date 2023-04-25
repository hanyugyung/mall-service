package org.example.domain.item

import lombok.Builder

class ItemInfo {

    class GetListOfItem(
        private val itemToken: String
        , private val name: String
        , private val price: Int
        , val itemOptionDtoList: List<GetListOfItemOption>
    ) {
        companion object {
            fun of(item: Item) : GetListOfItem {
                return GetListOfItem(
                    itemToken = item.itemToken
                    , name = item.name
                    , price = item.price
                    , itemOptionDtoList = item.itemOptionList
                        .map { GetListOfItemOption(it.stock, it.optionName, it.extraPrice) }
                )
            }
        }
    }

    class GetListOfItemOption(
        private val stock: Int
        , private val optionName: String
        , private val extraPrice: Int
    ) {
    }

}