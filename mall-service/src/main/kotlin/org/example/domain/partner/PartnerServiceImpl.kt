package org.example.domain.partner

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PartnerServiceImpl @Autowired constructor(
    private val partnerRepository: PartnerRepository
) : PartnerService {

    @Transactional
    override fun registerPartner(dto: PartnerCommand.RegisterPartner): String {
        val partner = dto.toEntity()
        return partnerRepository.save(partner)
    }

    @Transactional(readOnly = true)
    override fun getPartnerBrandInfo(): List<PartnerInfo.GetPartnerBrandInfo> {
        return partnerRepository.findAllByStatus(Partner.Status.ACTIVE)
            .map { PartnerInfo.GetPartnerBrandInfo(it.partnerToken, it.brandName) }
    }
}