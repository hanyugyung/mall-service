package org.example.domain;

import jakarta.persistence.*
import lombok.Getter
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.ZonedDateTime

@Getter
@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
open class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @CreationTimestamp
    lateinit var createdAt: ZonedDateTime private set

    @UpdateTimestamp
    lateinit var updatedAt: ZonedDateTime private set

}
