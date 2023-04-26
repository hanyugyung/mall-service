package org.example.domain.partner

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import lombok.Builder
import lombok.Getter
import lombok.NoArgsConstructor
import org.example.domain.Base

@Entity
@Table(name = "partners")
@Getter
@NoArgsConstructor
class Partner() : Base() {
    lateinit var partnerToken: String private set
    lateinit var email: String private set
    lateinit var password: String private set
    lateinit var bizNo: String private set
    lateinit var brandName: String private set

    @Enumerated(EnumType.STRING)
    var status: Status = Status.ACTIVE

    @Getter
    enum class Status {
        ACTIVE, INACTIVE
    }

    @Builder
    constructor(email: String, password: String, bizNo: String, brandName: String) : this() {
        this.partnerToken = System.currentTimeMillis().toString()
        this.email = email
        this.password = password
        this.bizNo = bizNo
        this.brandName = brandName
    }
}