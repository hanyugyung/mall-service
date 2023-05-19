package org.example.domain.item

class ItemInfo {

    class GetListOfItem(
        val itemToken: String
        , val name: String
        , val price: Int
    ) {
        companion object {
            fun of(item: Item): GetListOfItem {
                return GetListOfItem(
                    itemToken = item.itemToken, name = item.name, price = item.price
                )
            }
        }
    }

    class GetListOfItemOption(
        val stock: Int
        , val optionName: String
        , val extraPrice: Int
    ) {
        companion object {
            fun optionListOf(item: Item): List<GetListOfItemOption> {
                return item.itemOptionList.map {
                    GetListOfItemOption(
                        stock = it.stock, optionName = it.optionName, extraPrice = it.extraPrice
                    )
                }
            }
        }
    }

}