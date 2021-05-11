package com.br.projeto.pf.projectspringboot.service

import com.br.projeto.pf.projectspringboot.model.dto.UserDTO
import com.br.projeto.pf.projectspringboot.model.entity.Account
import com.br.projeto.pf.projectspringboot.model.entity.User
import com.br.projeto.pf.projectspringboot.repository.UserRepository
import javassist.NotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) : UserDetailsService {

    fun create(userDTO: UserDTO): User {
        val user = userDTO.let {
            User(
                id = null,
                userName = it.userName,
                password = passwordEncoder.encode(it.password),
                role = it.role,
                locked = it.locked,
                enable = it.enable,
                account = it.account
            )
        }
       return  userRepository.save(user)
    }

    fun getByName(name: String): User = userRepository.findByUserName(name).orElseThrow {
        throw(UsernameNotFoundException("user not found"))
    }

    fun getAll(): List<User> = userRepository.findAll()

    fun getById(id: Long): User = userRepository.findById(id).orElseThrow {
        throw(NotFoundException("user not found"))
    }

    @Throws(NotFoundException::class)
    fun update(id: Long, userDTO: UserDTO) {
        getById(id).let {
            userRepository.save(
                User(
                    id = id,
                    account = userDTO.account,
                    role = userDTO.role,
                    userName = userDTO.userName,
                    password = passwordEncoder.encode(it.password),
                    enable = userDTO.enable,
                    locked = userDTO.locked
                )
            )
        }
    }


    @Throws(NotFoundException::class)
    fun delete(id: Long) {
        val user = getById(id)
        userRepository.delete(user)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        return getByName(username)
    }
}