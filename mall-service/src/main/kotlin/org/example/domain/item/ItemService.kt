package org.example.domain.item

interface ItemService {
    fun registerItem(dto: ItemCommand.RegisterItem, partnerId: Long) : String
    fun getListOfItems(partnerToken: String) : List<ItemInfo.GetListOfItem>

    fun getItemOption(itemToken: String) : List<ItemInfo.GetListOfItemOption>
}