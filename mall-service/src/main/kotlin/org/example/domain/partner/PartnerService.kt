package org.example.domain.partner

interface PartnerService {
    fun registerPartner(dto: PartnerCommand.RegisterPartner) : String

    fun getPartnerBrandInfo() : List<PartnerInfo.GetPartnerBrandInfo>
}