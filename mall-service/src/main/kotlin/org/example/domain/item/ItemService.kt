package org.example.domain.item

interface ItemService {
    fun registerItem(dto: ItemCommand.RegisterItem, partnerId: Long) : String
    fun getListOfItems(partnerId: Long) : List<ItemInfo.GetListOfItem>
    fun getListOfItemsToUser(partnerToken: String): List<ItemInfo.GetListOfItem>
}