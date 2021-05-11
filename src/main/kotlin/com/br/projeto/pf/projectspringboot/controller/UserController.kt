package com.br.projeto.pf.projectspringboot.controller

import com.br.projeto.pf.projectspringboot.model.dto.UserDTO
import com.br.projeto.pf.projectspringboot.model.vo.UserVO
import com.br.projeto.pf.projectspringboot.service.UserService
import com.br.projeto.pf.projectspringboot.util.AUTHORITY_ADMIN_READ
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Positive

@Validated
@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @PostMapping
    fun create(@Valid @RequestBody userDTO: UserDTO): ResponseEntity<UserVO> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(UserVO(userService.create(userDTO)))
    }

    @PutMapping("/{id}")
    fun update(
        @Positive(message = "id cannot be less than or equal zero")
        @PathVariable id: Long,
        @Valid @RequestBody userDTO: UserDTO
    ): ResponseEntity.HeadersBuilder<*> =
        userService.update(id, userDTO).let { ResponseEntity.noContent() }


    @GetMapping("/{id}")
    fun getById(
        @Positive(message = "id cannot be less than or equal zero")
        @PathVariable id: Long
    ): ResponseEntity<UserVO> = ResponseEntity.ok(UserVO(userService.getById(id)))

    @GetMapping
    @PreAuthorize(AUTHORITY_ADMIN_READ)
    fun getAll(): ResponseEntity<List<UserVO>> =
        ResponseEntity.ok(userService.getAll().map { UserVO(it) })

    @DeleteMapping("/{id}")
    fun delete(
        @Positive(message = "id cannot be less than or equal zero")
        @PathVariable id: Long
    ): ResponseEntity.HeadersBuilder<*> =
        userService.delete(id).let { ResponseEntity.noContent() }
}