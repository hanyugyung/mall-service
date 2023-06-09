package org.example.domain.item

import lombok.Getter
import org.example.domain.item.option.ItemOption

class ItemCommand {

    class RegisterItem(
        private val name: String
        , private val price: Int
        , private val itemOptionDtoList: List<RegisterItemOption>
    ) {
        fun toEntity(partnerToken: String): Item {
            var item = Item(this.name, this.price, partnerToken)
            item.addItemOptionList(itemOptionDtoList.map { it.toEntity(item) }.toList())
            return item
        }
    }

    class RegisterItemOption(
        private val stock: Int
        , private val extraPrice: Int
        , private val optionName: String
    ) {
        fun toEntity(item: Item): ItemOption {
            return ItemOption(this.stock, this.extraPrice, this.optionName, item)
        }
    }

}