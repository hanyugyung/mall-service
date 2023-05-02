package org.example.domain.partner

interface PartnerRepository {
    fun store(partner: Partner)
    fun findBy(partnerToken: String): Partner?
    fun findAllBy(status: Partner.Status) : List<Partner>
    fun findByEmail(email: String): Partner?
}