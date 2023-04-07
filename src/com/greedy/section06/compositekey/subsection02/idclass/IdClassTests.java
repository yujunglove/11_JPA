package com.greedy.section06.compositekey.subsection02.idclass;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.greedy.section06.compositekey.subsection01.embedded.Member;
import com.greedy.section06.compositekey.subsection01.embedded.MemberPK;

public class IdClassTests {
	


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
	public void 아이디_클래스_사용한_복합키_테이블_매핑_테스트() {
		
		//giver
		Member member = new Member();
		member.setMemberPK(new MemberPK(1, "user01"));
		member.setPhone("010-1234-4040");
		member.setAddress("전라남도 목포시");
		
		//when
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(member);
		entityTransaction.commit();
		
		//then
		//제약 조건 딕셔너리뷰를 조회하여 복합키에 대한 제약 조건 확인
        String nativeSQL = "SELECT UC.CONSTRAINT_NAME, UC.CONSTRAINT_TYPE, UCC.COLUMN_NAME " +
                "FROM USER_CONSTRAINTS UC " +
                "JOIN USER_CONS_COLUMNS UCC ON (UC.CONSTRAINT_NAME = UCC.CONSTRAINT_NAME) " +
                "WHERE UCC.TABLE_NAME = 'TBL_MEMBER_SECTION06_SUBSECTION02'" +
                "AND UC.CONSTRAINT_TYPE = 'P'";
        
        List<Object[]> pkColumnList = entityManager.createNativeQuery(nativeSQL).getResultList();

		for(Object[] pkColumn : pkColumnList) {
			for(Object info : pkColumn) {
				System.out.print(info + " ");
			}
			System.out.println();
		}
	}

}
