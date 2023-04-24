package org.example.domain.item

import org.example.infrastructure.item.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ItemServiceImpl @Autowired constructor(
    private val itemRepository: ItemRepository
) : ItemService {

    override fun registerItem(dto: ItemCommand.RegisterItem): Item {
        val item = dto.toEntity()
        return itemRepository.save(item)
    }

    override fun getListOfItem(partnerId: Long): List<ItemInfo.GetListOfItem> {
        return itemRepository.findAllByPartnerId(partnerId)
            .map { ItemInfo.GetListOfItem.of(it) }
            .toList()
    }
}