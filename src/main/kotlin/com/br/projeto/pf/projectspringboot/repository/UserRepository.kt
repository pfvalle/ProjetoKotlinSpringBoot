package com.br.projeto.pf.projectspringboot.repository

import com.br.projeto.pf.projectspringboot.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<User, Long> {

    fun findByUserName(name: String): Optional<User>
}