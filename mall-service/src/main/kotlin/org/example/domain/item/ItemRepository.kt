package org.example.domain.item

interface ItemRepository {
    fun store(item: Item)
    fun findAllBy(partnerToken: String): List<Item>

    fun findBy(itemToken: String): Item?
}