package org.example.domain.item

import jakarta.persistence.EntityNotFoundException
import org.example.domain.partner.PartnerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemServiceImpl @Autowired constructor(
    private val itemRepository: ItemRepository, private val partnerRepository: PartnerRepository
) : ItemService {

    @Transactional
    override fun registerItem(dto: ItemCommand.RegisterItem, partnerToken: String): String {
        val item = dto.toEntity(partnerToken)
        itemRepository.store(item)
        return item.itemToken
    }

    @Transactional(readOnly = true)
    override fun getListOfItems(partnerToken: String): List<ItemInfo.GetListOfItem> {
        val partner =
            partnerRepository.findBy(partnerToken) ?: throw EntityNotFoundException("파트너가 존재하지 않습니다.")
        return itemRepository.findAllBy(partner.partnerToken)
            .map { ItemInfo.GetListOfItem.of(it) }
            .toList()
    }

    @Transactional(readOnly = true)
    override fun getItemOption(itemToken: String): List<ItemInfo.GetListOfItemOption> {
        val item = itemRepository.findBy(itemToken) ?: throw EntityNotFoundException("존재하지 않는 상품입니다.")
        return ItemInfo.GetListOfItemOption.optionListOf(item)
    }
}