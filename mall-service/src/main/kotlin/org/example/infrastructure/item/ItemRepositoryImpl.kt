package org.example.infrastructure.item

import org.example.domain.item.Item
import org.example.domain.item.ItemRepository
import org.springframework.stereotype.Repository

@Repository
class ItemRepositoryImpl constructor(
    val itemRepository: ItemJpaRepository
): ItemRepository {

    override fun store(item: Item) {
        itemRepository.save(item)
    }

    override fun findAllBy(partnerToken: String): List<Item> {
        return itemRepository.findAllByPartnerToken(partnerToken)
    }

    override fun findBy(itemToken: String): Item? {
        return itemRepository.findByItemToken(itemToken)
    }
}