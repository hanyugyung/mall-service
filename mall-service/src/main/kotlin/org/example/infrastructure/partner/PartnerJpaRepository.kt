package org.example.infrastructure.partner

import org.example.domain.partner.Partner
import org.springframework.data.jpa.repository.JpaRepository

interface PartnerJpaRepository : JpaRepository<Partner, Long> {
    fun findByPartnerToken(partnerToken: String): Partner?

    fun findByStatus(status: Partner.Status): List<Partner>
    fun findByEmail(email: String): Partner?
}