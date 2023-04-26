package org.example.infrastructure.partner

import org.example.domain.partner.Partner
import org.example.domain.partner.PartnerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class PartnerRepositoryImpl @Autowired constructor(
    private val partnerRepository: PartnerJpaRepository
) : PartnerRepository {
    override fun save(partner: Partner): String {
        return partnerRepository.save(partner).partnerToken
    }

    override fun findByPartnerToken(partnerToken: String): Partner? {
        return partnerRepository.findByPartnerToken(partnerToken)
    }

    override fun findAllByStatus(status: Partner.Status): List<Partner> {
        return partnerRepository.findByStatus(status)
    }
}