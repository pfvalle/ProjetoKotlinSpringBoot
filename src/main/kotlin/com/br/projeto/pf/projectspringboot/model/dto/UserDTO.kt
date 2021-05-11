package com.br.projeto.pf.projectspringboot.model.dto

import com.br.projeto.pf.projectspringboot.enum.UserRole
import com.br.projeto.pf.projectspringboot.model.entity.Account
import com.br.projeto.pf.projectspringboot.model.entity.User
import com.fasterxml.jackson.annotation.JsonInclude
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

data class UserDTO(
    @field:NotBlank(message = "user cannot be empty")
    val userName: String,
    @field:NotBlank(message = "password cannot be empty")
    val password: String,
    val account: Account,
    val role: UserRole,
    val enable: Boolean,
    val locked: Boolean,
)