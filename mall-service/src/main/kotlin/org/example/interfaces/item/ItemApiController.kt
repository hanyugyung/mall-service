package org.example.interfaces.item

import org.example.domain.item.ItemService
import org.example.interfaces.CommonResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/items")
class ItemApiController @Autowired constructor(
    private val itemService: ItemService
) {

    @GetMapping
    fun getListOfBrandItem(@RequestParam(required = true, name = "brand") partnerToken: String):
            CommonResponse<List<ItemApiDto.GetListOfItemResponse>> {

        return CommonResponse.successOf(
            itemService.getListOfItems(partnerToken)
                .map {
                    ItemApiDto.GetListOfItemResponse.of(it)
                })
    }

    @GetMapping("/{item-token}")
    fun getItemOption(@PathVariable(value="item-token") itemToken: String):
            CommonResponse<List<ItemApiDto.GetListOfItemOptionResponse>> {

        return CommonResponse.successOf(
            itemService.getItemOption(itemToken)
                .map {
                    ItemApiDto.GetListOfItemOptionResponse.of(it)
                })
    }

}