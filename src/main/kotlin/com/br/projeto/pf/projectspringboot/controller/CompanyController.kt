package com.br.projeto.pf.projectspringboot.controller

import com.br.projeto.pf.projectspringboot.model.dto.CompanyDTO
import com.br.projeto.pf.projectspringboot.model.vo.CompanyVO
import com.br.projeto.pf.projectspringboot.service.CompanyService
import com.br.projeto.pf.projectspringboot.util.AUTHORITY_ADMIN_WHITE
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Positive

@Validated
@RestController
@RequestMapping("/company")
class CompanyController(private val companyService: CompanyService) {

    @PostMapping
    @PreAuthorize(AUTHORITY_ADMIN_WHITE)
    fun create(@Valid @RequestBody companyDTO: CompanyDTO): ResponseEntity<CompanyVO> =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(CompanyVO(companyService.create(companyDTO)))

    @PutMapping("/{id}")
    @PreAuthorize(AUTHORITY_ADMIN_WHITE)
    fun update(
        @Positive(message = "id cannot be less than or equal zero")
        @PathVariable id: Long,
        @Valid @RequestBody companyDTO: CompanyDTO
    ): ResponseEntity<CompanyVO> =
        ResponseEntity.ok(CompanyVO(companyService.update(id, companyDTO)))

    @GetMapping("/{id}")
    fun getById(
        @Positive(message = "id cannot be less than or equal zero")
        @PathVariable id: Long
    ): ResponseEntity<CompanyVO> =
        ResponseEntity.ok(CompanyVO(companyService.getById(id)))

    @GetMapping
    fun getAll(): ResponseEntity<List<CompanyVO>> =
        ResponseEntity.ok(companyService.getAll().map { CompanyVO(it) })

    @DeleteMapping("/{id}")
    fun delete(
        @Positive(message = "id cannot be less than or equal zero")
        @PathVariable id: Long
    ): ResponseEntity.HeadersBuilder<*> =
        companyService.delete(id).let { ResponseEntity.noContent() }
}