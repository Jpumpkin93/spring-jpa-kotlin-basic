package com.jpumpkin.springjpakotlinbasic.entity

import javax.persistence.*


@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    val team: Team
) {
}