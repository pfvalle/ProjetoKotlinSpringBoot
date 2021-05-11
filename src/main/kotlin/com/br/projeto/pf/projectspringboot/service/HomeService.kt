package com.br.projeto.pf.projectspringboot.service

import com.br.projeto.pf.projectspringboot.model.vo.CompanyVO
import com.br.projeto.pf.projectspringboot.model.vo.HomeVO
import com.br.projeto.pf.projectspringboot.model.vo.UserVO
import org.springframework.stereotype.Service

@Service
class HomeService(
    private val companyService: CompanyService,
    private val userService: UserService
    ) {

    fun getHome(userName: String): HomeVO {
        val user = userService.getByName(userName)
        val companies = companyService.getAll()
        return HomeVO(
            UserVO(user),
            companies.filter {
                !user.account.companies.contains(it)
            }.map {
                CompanyVO(it)
            }
        )
    }
}