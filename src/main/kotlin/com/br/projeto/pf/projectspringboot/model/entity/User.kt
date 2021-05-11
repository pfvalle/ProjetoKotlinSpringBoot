package com.br.projeto.pf.projectspringboot.model.entity

import com.br.projeto.pf.projectspringboot.enum.UserRole
import com.br.projeto.pf.projectspringboot.model.dto.UserDTO
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(
    name = "users",
    uniqueConstraints = [
        UniqueConstraint(columnNames = arrayOf("id", "userName"))
    ]
)
data class User(
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    val id: Long?,
    @OneToOne
    @JoinColumn(name = "id_account")
    val account: Account,
    val role: UserRole,
    private val userName: String,
    private val password: String,
    val enable: Boolean,
    val locked: Boolean,

    ): UserDetails {

    constructor(userDTO: UserDTO): this(
        id = null,
        account = userDTO.account,
        role = userDTO.role,
        userName = userDTO.userName,
        password = userDTO.password,
        enable = userDTO.enable,
        locked = userDTO.locked
    )

    override fun getAuthorities(): Set<GrantedAuthority> {
        return this.role.grantedAuthorities()
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return userName
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return !locked
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return enable
    }
}
