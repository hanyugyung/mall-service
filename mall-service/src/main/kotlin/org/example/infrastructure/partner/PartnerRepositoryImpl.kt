package org.example.infrastructure.partner

import org.example.domain.partner.Partner
import org.example.domain.partner.PartnerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class PartnerRepositoryImpl @Autowired constructor(
    private val partnerRepository: PartnerJpaRepository
) : PartnerRepository {
    override fun store(partner: Partner) {
        partnerRepository.save(partner)
    }

    override fun findBy(partnerToken: String): Partner? {
        return partnerRepository.findByPartnerToken(partnerToken)
    }

    override fun findAllBy(status: Partner.Status): List<Partner> {
        return partnerRepository.findByStatus(status)
    }

    override fun findByEmail(email: String): Partner? {
        return partnerRepository.findByEmail(email)
    }
}