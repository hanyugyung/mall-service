package org.example.interfaces.partner

import org.example.domain.auth.AuthCommand
import org.example.domain.auth.AuthInfo
import org.example.domain.auth.UseRole
import org.example.domain.partner.PartnerCommand

class PartnerApiDto {

    class RegisterPartnerRequest(
        private val email: String,
        private val password: String,
        private val bizNo: String,
        private val brandName: String
    ) {
        fun toDomainDto(): PartnerCommand.RegisterPartner {
            return PartnerCommand.RegisterPartner(this.email, this.password, this.bizNo, this.brandName)
        }
    }

    class LoginPartnerRequest(
        private val email: String, private val password: String
    ) {
        fun toDomainDto(): AuthCommand.LoginPartner {
            return AuthCommand.LoginPartner(this.email, this.password)
        }
    }

    class LoginPartnerResponse(
        val token: String
        , val role: UseRole
    ) {
        companion object {
            fun of(domainDto: AuthInfo.Login): LoginPartnerResponse {
                return LoginPartnerResponse(domainDto.token, domainDto.role)
            }
        }
    }
}