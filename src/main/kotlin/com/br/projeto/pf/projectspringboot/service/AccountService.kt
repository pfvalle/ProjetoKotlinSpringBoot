package com.br.projeto.pf.projectspringboot.service

import com.br.projeto.pf.projectspringboot.model.dto.AccountDTO
import com.br.projeto.pf.projectspringboot.model.entity.Account
import com.br.projeto.pf.projectspringboot.repository.AccountRepository
import javassist.NotFoundException
import org.springframework.stereotype.Service

@Service
class AccountService(private val accountRepository: AccountRepository) {

    fun getById(id: Long): Account = accountRepository.findById(id).orElseThrow {
        throw(NotFoundException("account not found"))
    }

    fun getAll(): List<Account> = accountRepository.findAll()

    fun update(id: Long, accountDTO: AccountDTO) = getById(id).copy(
        name = accountDTO.name,
        email = accountDTO.email,
        phone = accountDTO.phone,
        companies = accountDTO.companies
    ).let {
        accountRepository.save(it)
    }

    fun delete(id: Long) = accountRepository.delete(getById(id))
}