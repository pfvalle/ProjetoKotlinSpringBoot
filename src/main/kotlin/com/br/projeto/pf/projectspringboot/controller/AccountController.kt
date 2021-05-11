package com.br.projeto.pf.projectspringboot.controller

import com.br.projeto.pf.projectspringboot.model.dto.AccountDTO
import com.br.projeto.pf.projectspringboot.model.vo.AccountVO
import com.br.projeto.pf.projectspringboot.service.AccountService
import com.br.projeto.pf.projectspringboot.util.AUTHORITY_ADMIN_READ
import com.br.projeto.pf.projectspringboot.util.AUTHORITY_NORMAL_READ
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Positive

@Validated
@RestController
@RequestMapping("/account")
class AccountController(private val accountService: AccountService) {

    @PutMapping("/{id}")
    @PreAuthorize(AUTHORITY_NORMAL_READ)
    fun update(
        @Positive(message = "id cannot be less than or equal zero")
        @PathVariable id: Long,
        @Valid @RequestBody accountDTO: AccountDTO): ResponseEntity<AccountVO> {
        return ResponseEntity.ok(AccountVO(accountService.update(id, accountDTO)))
    }

    @GetMapping("/{id}")
    fun getById(
        @Positive(message = "id cannot be less than or equal zero")
        @PathVariable id: Long
    ): ResponseEntity<AccountVO> = ResponseEntity.ok(AccountVO(accountService.getById(id)))

    @GetMapping
    @PreAuthorize(AUTHORITY_ADMIN_READ)
    fun getAll(): ResponseEntity<List<AccountVO>> {
        return ResponseEntity
            .ok(accountService.getAll().map { AccountVO(it) })
    }

    @DeleteMapping("/{id}")
    fun delete(
        @Positive(message = "id cannot be less than or equal zero")
        @PathVariable id: Long
    ): ResponseEntity.HeadersBuilder<*> {
        accountService.delete(id)
        return ResponseEntity.noContent()
    }
}