package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional  //롤백이 가능해진다
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(savedId));
        //true - 같은 Transaction 내에서 아이디 값(PK)이 똑같으면 같은 영속성 컨텍스트에서 같은 엔티티가 관리된다. -> 엔티티가 여러 개 생기지 않고 하나만 생성되어 관리된다.
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        //given

        //when

        //then
    }

}