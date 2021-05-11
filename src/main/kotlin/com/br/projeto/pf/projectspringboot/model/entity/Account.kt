package com.br.projeto.pf.projectspringboot.model.entity

import com.br.projeto.pf.projectspringboot.model.dto.AccountDTO
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(
    name = "accounts",
    uniqueConstraints = [
        UniqueConstraint(columnNames = arrayOf("id", "id_user"))
    ]
)
data class Account(
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    val id: Long?,
    @Column(name = "id_user")
    val idUser: Long,
    val name: String,
    val phone: String,
    val email: String,
    val birthDate: LocalDate,
    @ManyToMany
    @JoinTable(
        name = "accounts_companies",
        joinColumns = [JoinColumn(name = "id_account")],
        inverseJoinColumns = [JoinColumn(name = "id_company")]
    )
    val companies: List<Company> = emptyList()
) {
    constructor(accountDTO: AccountDTO): this(
        id = accountDTO.id,
        idUser = accountDTO.idUser,
        name = accountDTO.name,
        phone = accountDTO.phone,
        email = accountDTO.email,
        birthDate = accountDTO.birthDate,
        companies = accountDTO.companies
    )
}