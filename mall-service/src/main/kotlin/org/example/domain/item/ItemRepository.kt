package org.example.domain.item

interface ItemRepository {
    fun store(item: Item)
    fun findAllBy(partnerId: Long): List<Item>

    fun findBy(itemToken: String): Item?
}