package com.jpumpkin.springjpakotlinbasic.entitiy

import com.jpumpkin.springjpakotlinbasic.entity.*
import com.jpumpkin.springjpakotlinbasic.entity.QMember.member
import com.querydsl.jpa.impl.JPAQueryFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull
import javax.persistence.EntityManager


@DataJpaTest
internal class MemberRepositoryTest {

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Autowired
    lateinit var teamRepository: TeamRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun `멤버 entity lazy loading test`() {
        val savedTeam = teamRepository.save(
            Team(name = "JpumpkinTeam")
        )
        val savedMember = memberRepository.save(
            Member(
                name = "Jpumpkin",
                team = savedTeam
            )
        )

        //영속성 컨텍스트 초기화
        entityManager.clear()

        val member = memberRepository.findByIdOrNull(savedMember.id!!)
    }

    @Test
    fun `querydsl을 이용한 멤버 조회`() {
        val jpaQueryFactory = JPAQueryFactory(entityManager)

        val savedTeam = teamRepository.save(
            Team(name = "JpumpkinTeam")
        )
        val savedMember = memberRepository.save(
            Member(
                name = "Jpumpkin",
                team = savedTeam
            )
        )

        //영속성 컨텍스트 초기화
        entityManager.clear()

        val findMember: Member = jpaQueryFactory
            .selectFrom(member)
            .fetchOne() ?: throw NullPointerException()

        assertThat(findMember.name).isEqualTo("Jpumpkin")
    }
}