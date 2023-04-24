package org.example.domain.partner

interface PartnerService {
    fun registerPartner(dto: PartnerCommand.RegisterPartner) : Partner
}