package org.example.domain.item

import jakarta.persistence.EntityNotFoundException
import org.example.infrastructure.item.ItemRepository
import org.example.infrastructure.partner.PartnerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemServiceImpl @Autowired constructor(
    private val itemRepository: ItemRepository
    , private val partnerRepository: PartnerRepository
) : ItemService {

    @Transactional
    override fun registerItem(dto: ItemCommand.RegisterItem, partnerId: Long): String {
        val item = dto.toEntity(partnerId)
        return itemRepository.save(item).itemToken
    }

    @Transactional(readOnly = true)
    override fun getListOfItems(partnerId: Long): List<ItemInfo.GetListOfItem> {
        return itemRepository.findAllByPartnerId(partnerId)
            .map { ItemInfo.GetListOfItem.of(it) }
            .toList()
    }

    @Transactional(readOnly = true)
    override fun getListOfItemsToUser(partnerToken: String): List<ItemInfo.GetListOfItem> {
        val partner = partnerRepository.findByPartnerToken(partnerToken) ?: throw EntityNotFoundException("파트너가 존재하지 않습니다.")
        return getListOfItems(partner.id!!)
    }
}