package com.br.projeto.pf.projectspringboot.model

class ErrorResponse(
    val message: String?,
    val data: List<Error> = emptyList()
)

class Error(
    val field: String?,
    val message: String?
)