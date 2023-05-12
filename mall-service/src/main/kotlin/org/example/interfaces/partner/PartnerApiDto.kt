package org.example.interfaces.partner

import org.example.domain.partner.PartnerCommand

class PartnerApiDto {

    class RegisterPartner(
        private val email: String
        , private val password: String
        , private val bizNo: String
        , private val brandName: String
    ) {
        fun toDomainDto(): PartnerCommand.RegisterPartner {
            return PartnerCommand.RegisterPartner(this.email, this.password, this.bizNo, this.brandName)
        }
    }

}