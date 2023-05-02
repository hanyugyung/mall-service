package org.example.domain.partner

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PartnerServiceImpl @Autowired constructor(
    private val partnerRepository: PartnerRepository
) : PartnerService {

    private fun existEmailAlready(email: String) {
        if(partnerRepository.findByEmail(email) != null) throw IllegalArgumentException("이미 존재하는 파트너 입니다.")
    }

    @Transactional
    override fun registerPartner(dto: PartnerCommand.RegisterPartner): String {
        existEmailAlready(dto.email)
        val partner = dto.toEntity()
        partnerRepository.store(partner)
        return partner.partnerToken
    }

    @Transactional(readOnly = true)
    override fun getPartnerBrandInfo(): List<PartnerInfo.GetPartnerBrandInfo> {
        return partnerRepository.findAllBy(Partner.Status.ACTIVE)
            .map { PartnerInfo.GetPartnerBrandInfo(it.partnerToken, it.brandName) }
    }
}