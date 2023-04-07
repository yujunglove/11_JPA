package com.greedy.section001.enumtype;

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

import com.greedy.section04.enumtype.Member;
import com.greedy.section04.enumtype.RoleType;

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
	
	@Test
	public void enum타입_매핑_테스트() {
		
		//given
		
		Product product = new Product();
		
		product.setProductNo(1);
		product.setProductName("닌텐도 스위치 피카츄 에디션");
		product.setProductPrice(300000);
		product.setReleaseDate(new java.sql.Date(System.currentTimeMillis()));
		product.setProductRole(RoleType.MEMBER);
		
//		Member member = new Member();
//		member.setMemberNo(1);
//		member.setMemberId("user01");
//		member.setMemberPwd("pass01");
//		member.setNickname("홍길동");
//		member.setPhone("010-1234-5678");
//		member.setAddress("서울시 종로구");
//		member.setEnrollDate(new java.sql.Date(System.currentTimeMillis()));
//		member.setMemberRole(RoleType.MEMBER);
//		member.setStatus("Y");
		
		//when
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(product);
		entityTransaction.commit();
		
		//then
		Product foundProduct= entityManager.find(Product.class,product.getProductNo());
		assertEquals(product.getProductNo(), foundProduct.getProductNo());
		System.out.println(foundProduct);
	}

}
