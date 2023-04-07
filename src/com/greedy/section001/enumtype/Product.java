package com.greedy.section001.enumtype;

import java.sql.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.greedy.section04.enumtype.RoleType;

@Entity(name="section001_product")
//매핑 될 테이블의 이름을 작성한다. 생략하면 자동으로 클래스의 이름을 테이블의 이름으로 사용한다.
@Table(name="TBL_PRODUCT")
@Access(AccessType.PROPERTY)
public class Product {
	
//	@Id
	@Column(name="PRODUCT_NO", nullable=false)
	private int productNo;
	
	@Column(name="PRODUCT_NAME", nullable=false)
	private String productName;
	
	@Column(name="PRODUCT_PRICE", nullable=false)
	private int productPrice;
	
	@Column(name="RELEASE_DATE", nullable=false)
	private java.sql.Date releaseDate;
	
	
	@Column(name="PRODUCT_ROLE", nullable=false)
	@Enumerated(EnumType.STRING)
	private RoleType productRole;

	public Product() {
	}

	public Product(int productNo, String productName, int productPrice, Date releaseDate, RoleType productRole) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.productPrice = productPrice;
		this.releaseDate = releaseDate;
		this.productRole = productRole;
	}
	@Id
	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public java.sql.Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(java.sql.Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public RoleType getProductRole() {
		return productRole;
	}

	public void setProductRole(RoleType productRole) {
		this.productRole = productRole;
	}

	@Override
	public String toString() {
		return "Member [productNo=" + productNo + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", releaseDate=" + releaseDate + ", productRole=" + productRole + "]";
	}

	
}
