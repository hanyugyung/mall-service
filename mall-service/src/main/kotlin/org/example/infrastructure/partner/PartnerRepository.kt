package org.example.infrastructure.partner

import org.example.domain.partner.Partner
import org.springframework.data.jpa.repository.JpaRepository

interface PartnerRepository : JpaRepository<Partner, Long> {
}