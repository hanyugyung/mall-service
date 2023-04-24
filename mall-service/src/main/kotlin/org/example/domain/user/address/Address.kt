package org.example.domain.user.address

import jakarta.persistence.*
import lombok.Getter
import lombok.NoArgsConstructor
import org.example.domain.Base
import org.example.domain.user.User

@Entity
@Table(name = "addresses")
@Getter
@NoArgsConstructor
class Address() : Base() {

    lateinit var addressToken: String private set
    lateinit var address1: String private set
    lateinit var address2: String private set
    lateinit var zipCode: String private set

    @ManyToOne
    lateinit var user: User
}