package com.mybatis.demo.model;

import java.sql.Date;

import org.springframework.hateoas.ResourceSupport;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Orders  extends ResourceSupport{
	
	private Integer productOrderId;
	private Integer productUserId;
	private String productAmount;
	private Date productOrderedDate;
	private Date productSaleDate;
	private Date productShippedDate;
	
	/**
	 * 
	 */
	public Orders() {
		super();
	}
    
	/**
	 * @param productOrderId
	 * @param productUserId
	 * @param productAmount
	 * @param productOrderedDate
	 * @param productSaleDate
	 * @param productShippedDate
	 */
	public Orders(Integer productOrderId, Integer productUserId, String productAmount, Date productOrderedDate,
			Date productSaleDate, Date productShippedDate) {
		super();
		this.productOrderId = productOrderId;
		this.productUserId = productUserId;
		this.productAmount = productAmount;
		this.productOrderedDate = productOrderedDate;
		this.productSaleDate = productSaleDate;
		this.productShippedDate = productShippedDate;
	}

	/**
	 * @param productUserId
	 * @param productAmount
	 * @param productOrderedDate
	 * @param productSaleDate
	 * @param productShippedDate
	 */
	public Orders(Integer productUserId, String productAmount, Date productOrderedDate,
			Date productSaleDate, Date productShippedDate) {
		super();
		this.productUserId = productUserId;
		this.productAmount = productAmount;
		this.productOrderedDate = productOrderedDate;
		this.productSaleDate = productSaleDate;
		this.productShippedDate = productShippedDate;
	}
	

	/**
	 * @return the productAmount
	 */
	public String getProductAmount() {
		return productAmount;
	}

	/**
	 * @return the productOrderedDate
	 */
	public Date getProductOrderedDate() {
		return productOrderedDate;
	}

	/**
	 * @return the productOrderId
	 */
	public Integer getProductOrderId() {
		return productOrderId;
	}

	/**
	 * @return the productSaleDate
	 */
	public Date getProductSaleDate() {
		return productSaleDate;
	}

	/**
	 * @return the productShippedDate
	 */
	public Date getProductShippedDate() {
		return productShippedDate;
	}

	/**
	 * @return the productUserId
	 */
	public Integer getProductUserId() {
		return productUserId;
	}

	/**
	 * @param productAmount
	 *            the productAmount to set
	 */
	public void setProductAmount(String productAmount) {
		this.productAmount = productAmount;
	}

	/**
	 * @param productOrderedDate
	 *            the productOrderedDate to set
	 */
	public void setProductOrderedDate(Date productOrderedDate) {
		this.productOrderedDate = productOrderedDate;
	}

	/**
	 * @param productOrderId
	 *            the productOrderId to set
	 */
	@JsonCreator
	public void setProductOrderId(@JsonProperty("productOrderId") Integer productOrderId) {
		this.productOrderId = productOrderId;
	}

	/**
	 * @param productSaleDate
	 *            the productSaleDate to set
	 */
	public void setProductSaleDate(Date productSaleDate) {
		this.productSaleDate = productSaleDate;
	}

	/**
	 * @param productShippedDate
	 *            the productShippedDate to set
	 */
	public void setProductShippedDate(Date productShippedDate) {
		this.productShippedDate = productShippedDate;
	}

	/**
	 * @param productUserId
	 *            the productUserId to set
	 */
	public void setProductUserId(Integer productUserId) {
		this.productUserId = productUserId;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Orders [productOrderId=" + productOrderId + ", productUserId=" + productUserId + ", productAmount="
				+ productAmount + ", productOrderedDate=" + productOrderedDate + ", productSaleDate=" + productSaleDate
				+ ", productShippedDate=" + productShippedDate + "]";
	}

	
}
