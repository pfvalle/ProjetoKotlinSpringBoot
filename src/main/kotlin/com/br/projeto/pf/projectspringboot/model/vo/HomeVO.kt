package com.br.projeto.pf.projectspringboot.model.vo

data class HomeVO(
    val user: UserVO,
    val companies: List<CompanyVO>
)