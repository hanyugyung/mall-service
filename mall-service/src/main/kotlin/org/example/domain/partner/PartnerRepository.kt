package org.example.domain.partner

interface PartnerRepository {
    fun save(partner: Partner): String
    fun findByPartnerToken(partnerToken: String): Partner?
    fun findAllByStatus(status: Partner.Status) : List<Partner>
}