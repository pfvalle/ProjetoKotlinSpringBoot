package com.br.projeto.pf.projectspringboot.controller


import com.br.projeto.pf.projectspringboot.service.HomeService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping
class TemplateController(private val homeService: HomeService) {

    @GetMapping("/login")
    fun getLogin(modelMap: ModelMap): String{
        return "Login"
    }

    @GetMapping("/home")
    fun getHome(model: Model): String {
        val name = SecurityContextHolder.getContext().authentication.name
        val home = homeService.getHome(name)
        model.addAttribute(home)
        return "Home"
    }
}