package org.example.domain.partner

import org.example.infrastructure.partner.PartnerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PartnerServiceImpl @Autowired constructor(
    private val partnerRepository: PartnerRepository
) : PartnerService {

    override fun registerPartner(dto: PartnerCommand.RegisterPartner): Partner {
        val partner = dto.toEntity()
        return partnerRepository.save(partner)
    }
}