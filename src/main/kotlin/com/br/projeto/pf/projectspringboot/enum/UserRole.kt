package com.br.projeto.pf.projectspringboot.enum

import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class UserRole {
    ADMIN {
        override fun userPermissions() = setOf(
            UserPermission.ADMIN_READ,
            UserPermission.ADMIN_WRITE,
            UserPermission.NORMAL_READ,
            UserPermission.NORMAL_WRITE
        )
        override fun grantedAuthorities() = setOf(
            SimpleGrantedAuthority("ROLE_${this.name}"),
            SimpleGrantedAuthority(UserPermission.ADMIN_READ.permission),
            SimpleGrantedAuthority(UserPermission.ADMIN_WRITE.permission),
            SimpleGrantedAuthority(UserPermission.NORMAL_READ.permission),
            SimpleGrantedAuthority(UserPermission.NORMAL_WRITE.permission)
        )
    },
    NORMAL {
        override fun userPermissions() = setOf(
            UserPermission.NORMAL_READ,
            UserPermission.NORMAL_WRITE
        )
        override fun grantedAuthorities() = setOf(
            SimpleGrantedAuthority("ROLE_${this.name}"),
            SimpleGrantedAuthority(UserPermission.NORMAL_READ.permission),
            SimpleGrantedAuthority(UserPermission.NORMAL_WRITE.permission)
        )
    };

    abstract fun userPermissions(): Set<UserPermission>
    abstract fun grantedAuthorities(): Set<SimpleGrantedAuthority>
}