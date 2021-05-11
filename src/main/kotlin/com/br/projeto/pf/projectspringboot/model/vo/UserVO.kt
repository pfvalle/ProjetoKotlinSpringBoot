package com.br.projeto.pf.projectspringboot.model.vo

import com.br.projeto.pf.projectspringboot.model.entity.User

data class UserVO(
    val userName: String,
    val account: AccountVO
) {
    constructor(user: User): this(
        userName = user.username,
        account = AccountVO(user.account)
    )
}