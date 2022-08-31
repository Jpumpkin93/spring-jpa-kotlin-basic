package com.jpumpkin.springjpakotlinbasic.entitiy

import com.jpumpkin.springjpakotlinbasic.entity.Member
import com.jpumpkin.springjpakotlinbasic.entity.MemberRepository
import com.jpumpkin.springjpakotlinbasic.entity.Team
import com.jpumpkin.springjpakotlinbasic.entity.TeamRepository
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
}