package org.example.interfaces.partner;

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/partners")
class PartnerController {

    @PostMapping("/register")
    fun registerPartner(@RequestBody @Valid requestDto: PartnerApiDto.RegisterPartner ) {

    }

}
