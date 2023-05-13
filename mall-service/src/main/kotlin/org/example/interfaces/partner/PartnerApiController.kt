package org.example.interfaces.partner;

import jakarta.validation.Valid
import org.example.domain.partner.PartnerService
import org.example.interfaces.CommonResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/partners")
class PartnerApiController @Autowired constructor(
    private val partnerService: PartnerService
){

    @PostMapping("/register")
    fun registerPartner(@RequestBody @Valid requestDto: PartnerApiDto.RegisterPartner ) : CommonResponse<*> {
        partnerService.registerPartner(requestDto.toDomainDto())
        return CommonResponse.successOf()
    }

}
