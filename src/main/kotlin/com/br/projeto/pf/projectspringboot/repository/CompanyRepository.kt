package com.br.projeto.pf.projectspringboot.repository

import com.br.projeto.pf.projectspringboot.model.entity.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository: JpaRepository<Company, Long>