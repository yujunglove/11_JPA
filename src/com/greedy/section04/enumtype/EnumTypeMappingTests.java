package com.greedy.section04.enumtype;

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


public class EnumTypeMappingTests {
	

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
	
	//엔티티와 테이블을 정확하게 매핑하는 것이 JPA의 핵심이다.
	//이를 위해 다양한 매핑 어노테이션이 지원되는데 이 부분에 대해서 학습해 보자
	
	@Test
	public void enum타입_매핑_테스트() {
		
		//given
		Member member = new Member();
		member.setMemberNo(1);
		member.setMemberId("user01");
		member.setMemberPwd("pass01");
		member.setNickname("홍길동");
		member.setPhone("010-1234-5678");
		member.setAddress("서울시 종로구");
		member.setEnrollDate(new java.sql.Date(System.currentTimeMillis()));
		member.setMemberRole(RoleType.MEMBER);
		member.setStatus("Y");
		
		//when
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(member);
		entityTransaction.commit();
		
		//then
		Member foundMember = entityManager.find(Member.class, member.getMemberNo());
		assertEquals(member.getMemberNo(), foundMember.getMemberNo());
		System.out.println(foundMember);
	}

}
