package org.example.domain.item

interface ItemRepository {
    fun save(item: Item): String
    fun findAllByPartnerId(partnerId: Long): List<Item>
}