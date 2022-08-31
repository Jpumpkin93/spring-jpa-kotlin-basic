package com.jpumpkin.springjpakotlinbasic.entity

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {
}