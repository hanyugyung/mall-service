package org.example.infrastructure.item

import org.example.domain.item.Item
import org.example.domain.item.ItemRepository
import org.springframework.stereotype.Repository

@Repository
class ItemRepositoryImpl constructor(
    val itemRepository: ItemJpaRepository
): ItemRepository {

    override fun save(item: Item): String {
        return itemRepository.save(item).itemToken
    }

    override fun findAllByPartnerId(partnerId: Long): List<Item> {
        return itemRepository.findAllByPartnerId(partnerId)
    }
}