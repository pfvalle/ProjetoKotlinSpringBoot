package com.br.projeto.pf.projectspringboot.model.entity

import com.br.projeto.pf.projectspringboot.model.dto.CompanyDTO
import javax.persistence.*

@Entity
@Table(
    name = "companies",
    uniqueConstraints = [
        UniqueConstraint(columnNames = arrayOf("id"))
    ]
)
data class Company(
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    val id: Long?,
    val name: String,
    val phone: String,
    val email: String
) {
    constructor(company: CompanyDTO): this(
        id = null,
        name = company.name,
        phone = company.phone,
        email = company.email
    )
}