package com.mybatis.demo.model;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Products extends ResourceSupport {

	private Integer productId;
	private String productName;
	private String productVersion;
	private String productView;
	private String productDesc;

	/**
	 * 
	 */
	public Products() {
		super();

	}

	/**
	 * @param productName
	 * @param productVersion
	 * @param productView
	 * @param productDesc
	 */
	public Products(String productName, String productVersion, String productView,
			String productDesc) {
		super();
		this.productName = productName;
		this.productVersion = productVersion;
		this.productView = productView;
		this.productDesc = productDesc;
	}

	/**
	 * @param productId
	 * @param productName
	 * @param productVersion
	 * @param productView
	 * @param productDesc
	 */
	public Products(Integer productId, String productName, String productVersion,
			String productView, String productDesc) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productVersion = productVersion;
		this.productView = productView;
		this.productDesc = productDesc;
	}



	/**
	 * @return the productDesc
	 */
	public String getProductDesc() {
		return productDesc;
	}



	/**
	 * @param productDesc the productDesc to set
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}



	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @return the productVersion
	 */
	public String getProductVersion() {
		return productVersion;
	}

	/**
	 * @return the productView
	 */
	public String getProductView() {
		return productView;
	}


	/**
	 * @param productId
	 *            the productId to set
	 */
	@JsonCreator
	public void setProductId(@JsonProperty("productId") Integer productId) {
		this.productId = productId;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @param productVersion
	 *            the productVersion to set
	 */
	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}

	/**
	 * @param productView
	 *            the productView to set
	 */
	public void setProductView(String productView) {
		this.productView = productView;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Products [productId=" + productId + ", productName=" + productName + ", productVersion="
				+ productVersion + ", productView=" + productView + "]";
	}
	

}
