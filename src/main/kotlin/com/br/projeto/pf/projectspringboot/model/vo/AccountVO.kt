package com.br.projeto.pf.projectspringboot.model.vo

import com.br.projeto.pf.projectspringboot.model.entity.Account
import com.br.projeto.pf.projectspringboot.model.entity.Company
import java.time.LocalDate
import javax.validation.constraints.NotEmpty

data class AccountVO(
    val id: Long? = null,
    val name: String,
    val phone: String,
    val email: String,
    val birthDate: LocalDate,
    val companies: List<Company> = emptyList()
) {
    constructor(account: Account): this(
        id = account.id,
        name = account.name,
        phone = account.phone,
        email = account.email,
        birthDate = account.birthDate,
        companies = account.companies
    )
}