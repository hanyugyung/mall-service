package org.example.domain.user;

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import lombok.Getter
import lombok.NoArgsConstructor
import org.example.domain.Base
import org.example.domain.user.address.Address

@Entity
@Getter
@NoArgsConstructor
class User() : Base() {

    lateinit var userToken: String private set
    lateinit var email: String private set
    lateinit var password: String private set
    lateinit var role: Role private set

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.PERSIST])
    var addressList: List<Address> = listOf()

    @Getter
    enum class Role {
        ADMIN, CUSTOMER
    }

    constructor(email: String, password: String) : this() {
        this.userToken = System.currentTimeMillis().toString()
        this.email = email
        this.password = password
    }
}
