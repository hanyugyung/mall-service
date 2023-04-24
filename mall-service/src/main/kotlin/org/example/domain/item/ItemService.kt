package org.example.domain.item

interface ItemService {
    fun registerItem(dto: ItemCommand.RegisterItem) : Item
    fun getListOfItem(partnerId: Long) : List<ItemInfo.GetListOfItem>
}