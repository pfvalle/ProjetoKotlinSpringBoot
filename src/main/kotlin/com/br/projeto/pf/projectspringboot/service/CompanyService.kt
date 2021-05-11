package com.br.projeto.pf.projectspringboot.service

import com.br.projeto.pf.projectspringboot.model.dto.CompanyDTO
import com.br.projeto.pf.projectspringboot.model.entity.Company
import com.br.projeto.pf.projectspringboot.repository.CompanyRepository
import javassist.NotFoundException
import org.springframework.stereotype.Service

@Service
class CompanyService(private val companyRepository: CompanyRepository) {

    fun create(companyDTO: CompanyDTO): Company = companyRepository.save(Company(companyDTO))

    fun getById(id: Long): Company = companyRepository.findById(id).orElseThrow {
        throw(NotFoundException("company not found"))
    }

    fun getAll(): List<Company> = companyRepository.findAll()

    fun update(id: Long, companyDTO: CompanyDTO): Company =
        getById(id).copy(
            name = companyDTO.name,
            email = companyDTO.email,
            phone = companyDTO.phone
        ).let {
            companyRepository.save(it)
        }

    fun delete(id: Long) {
        companyRepository.delete(getById(id))
    }
}
