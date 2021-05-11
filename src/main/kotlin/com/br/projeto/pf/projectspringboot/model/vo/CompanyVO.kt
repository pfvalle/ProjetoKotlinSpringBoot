package com.br.projeto.pf.projectspringboot.model.vo

import com.br.projeto.pf.projectspringboot.model.entity.Company
import javax.validation.constraints.NotEmpty

data class CompanyVO(
    val id: Long?,
    val name: String,
    val phone: String,
    val email: String
) {
    constructor(company: Company): this(
        id = company.id,
        name = company.name,
        phone = company.phone,
        email = company.email
    )
}