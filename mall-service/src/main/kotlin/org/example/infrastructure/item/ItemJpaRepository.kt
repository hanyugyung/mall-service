package org.example.infrastructure.item

import org.example.domain.item.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemJpaRepository : JpaRepository<Item, Long> {
    fun findAllByPartnerId(partnerId: Long) : List<Item>
    fun findByItemToken(itemToken: String) : Item?
}