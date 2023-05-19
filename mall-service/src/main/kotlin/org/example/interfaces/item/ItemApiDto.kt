package org.example.interfaces.item

import org.example.domain.item.ItemInfo

class ItemApiDto {

    class GetListOfItemResponse(
        private val itemToken: String
        , private val name: String
        , private val price: Int
    ) {
        companion object {
            fun of(domainDto: ItemInfo.GetListOfItem) : GetListOfItemResponse {
                return GetListOfItemResponse(
                    itemToken = domainDto.itemToken
                    , name = domainDto.name
                    , price = domainDto.price
                )
            }
        }
    }

    class GetListOfItemOptionResponse(
        private val stock: Int
        , private val optionName: String
        , private val extraPrice: Int
    ) {
        companion object {
            fun of(domainDto: ItemInfo.GetListOfItemOption) : GetListOfItemOptionResponse {
                return GetListOfItemOptionResponse(
                    stock = domainDto.stock
                    , optionName = domainDto.optionName
                    , extraPrice = domainDto.extraPrice
                )
            }
        }
    }

}