package org.example.infrastructure.item

import org.example.domain.item.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<Item, Long> {
    fun findAllByPartnerId(partnerId: Long) : List<Item>
}