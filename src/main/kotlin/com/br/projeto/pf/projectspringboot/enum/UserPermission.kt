package com.br.projeto.pf.projectspringboot.enum

enum class UserPermission(val permission: String) {
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    NORMAL_READ("normal:read"),
    NORMAL_WRITE("normal:write")
}