package com.br.projeto.pf.projectspringboot.model.dto

import javax.validation.constraints.NotBlank

data class CompanyDTO(
    @field:NotBlank(message = "name cannot be empty")
    val name: String,
    @field:NotBlank(message = "phone cannot be empty")
    val phone: String,
    @field:NotBlank(message = "email cannot be empty")
    val email: String
)