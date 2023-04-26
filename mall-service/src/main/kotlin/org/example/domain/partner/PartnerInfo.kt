package org.example.domain.partner

class PartnerInfo {
    class GetPartnerBrandInfo(
        private val partnerToken: String
        , private val brandName: String
    ) {
        companion object {
            fun of(partner: Partner): GetPartnerBrandInfo {
                return GetPartnerBrandInfo(partner.partnerToken, partner.brandName)
            }
        }
    }
}