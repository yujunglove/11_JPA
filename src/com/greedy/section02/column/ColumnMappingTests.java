package com.greedy.section02.column;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ColumnMappingTests {


	private static EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	@BeforeAll
	public static void initFactory() {
		entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
	}
	
	@BeforeEach
	public void initManager() {
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	@AfterAll
	public static void closeFactory() {
		entityManagerFactory.close();
	}
	
	@AfterEach
	public void closeManager() {
		entityManager.close();
	}
	
	@Test
	public void 컬럼에서_사용하는_속성_테스트() {
		//given
		Member member = new Member();
		member.setMemberNo(1);
		member.setMemberId("user01");
		member.setMemberPwd("pass01");
		member.setNickname("홍길동");
		member.setPhone("010-1234-5678");
		member.setAddress("서울시 종로구");
		member.setEnrollDate(new java.sql.Date(System.currentTimeMillis()));
		member.setMemberRole("ROLE_MEMBER");
		member.setStatus("Y");
		
		//when
		entityManager.persist(member);
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(member);
		
		//실제 디비에도 완전히 반영되게
		entityTransaction.commit();
		
		//then
		Member foundMember = entityManager.find(Member.class, member.getMemberNo());
		assertEquals(member.getMemberNo(), foundMember.getMemberNo());
		
		//엔티티와 엔티티에서 사용되었던 필드 값으로 구문이 작성되어 있다.
		String jpql ="SELECT TO_CHAR(A.enrollDate, 'YYYY/MM/DD HH:mi:ss') FROM section02_member A WHERE A.memberNo = 1";
		String dateTime = entityManager.createQuery(jpql, String.class).getSingleResult();
		System.out.println(dateTime);
		
	}

}
