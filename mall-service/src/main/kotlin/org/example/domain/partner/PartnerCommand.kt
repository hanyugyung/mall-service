package org.example.domain.partner

class PartnerCommand {

    class RegisterPartner(
        val email: String
        , private val password: String
        , private val bizNo: String
        , private val brandName: String
    ) {
        fun toEntity(): Partner {
            return Partner(this.email, this.password, this.bizNo, this.brandName)
        }
    }

}