package com.br.projeto.pf.projectspringboot.util

const val ADMIN_READ = "'admin:read'"
const val ADMIN_WRITE = "'admin:write'"
const val NORMAL_READ = "'normal:read'"
const val AUTHORITY_ADMIN_READ = "hasAuthority($ADMIN_READ)"
const val AUTHORITY_ADMIN_WHITE = "hasAuthority($ADMIN_WRITE)"
const val AUTHORITY_NORMAL_READ = "hasAuthority($NORMAL_READ)"
