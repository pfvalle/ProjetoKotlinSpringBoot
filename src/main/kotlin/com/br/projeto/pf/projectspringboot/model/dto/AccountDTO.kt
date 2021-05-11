package com.br.projeto.pf.projectspringboot.model.dto

import com.br.projeto.pf.projectspringboot.model.entity.Company
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

data class AccountDTO(
    val id: Long? = null,
    val idUser: Long,
    @field:NotBlank(message = "name cannot be empty")
    val name: String,
    @field:NotBlank(message = "phone cannot be empty")
    val phone: String,
    @field:NotBlank(message = "email cannot be empty")
    val email: String,
    val birthDate: LocalDate,
    val companies: List<Company> = emptyList()
)