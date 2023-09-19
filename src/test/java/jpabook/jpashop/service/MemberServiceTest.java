package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)    //jUnit 실행할 때 스프링이랑 같이 엮어서 실행
@SpringBootTest //스프링 부트를 띄운 상태에서 테스트할 수 있다 -> 스프링 컨테이너 안에서 테스트를 실행
@Transactional  //스프링의 Transactional은 테스트 코드에서 transaction commit을 하지 않고 롤백을 한다. (테스트가 끝나면 롤백된다)
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        em.flush();
        assertEquals(member, memberRepository.findOne(savedId));
        //true - 같은 Transaction 내에서 아이디 값(PK)이 똑같으면 같은 영속성 컨텍스트에서 같은 엔티티가 관리된다. -> 엔티티가 여러 개 생기지 않고 하나만 생성되어 관리된다.
    }

    //memberService.join(member2);에서 발생한 예외가 IllegalStateException이면 된다.
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);        //예외가 발생해야 한다.

        //then
        //위에서 예외가 발생해야해서 then쪽으로 오면 안된다. 만약에 여기에 오면 잘못된 것이기 때문에 fail을 떨군다.
        fail("예외가 발생해야 한다.");
    }

}