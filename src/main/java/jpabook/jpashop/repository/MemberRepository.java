package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member); //영속성 컨텍스트에 member 객체를 넣는다. 나중에 트랜젝션이 commit 되는 시점에 DB에 반영된다.(-> DB에 insert 쿼리가 날아간다)
    }

    public Member findOne(Long id){
        //find(타입, PK) -> 단건 조회
        return em.find(Member.class, id);
    }

    //createQuery(JPQL, 반환 타입)
    //SQL은 테이블을 대상으로 쿼리, JPQL은 Entity 객체를 대상으로 쿼리
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name){
        //:name -> 파라미터를 바인딩
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
