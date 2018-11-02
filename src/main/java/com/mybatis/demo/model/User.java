package com.mybatis.demo.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class User extends ResourceSupport {
	private String eyeColor;
	private Integer height;
	private Integer weight;
	private Date birthday;
	private Integer userAge;
	private Integer userId;
	private String userName;
	//@ManyToOne(mappedBy = "Orders")
	private List<Orders> userOrderList = new ArrayList<>();

	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * @param userId
	 * @param userName
	 * @param eyeColor
	 * @param height
	 * @param weight
	 * @param birthday
	 * @param userAge
	 */
	public User(Integer userId, String userName, String eyeColor, Integer height, Integer weight, Date birthday,
			Integer userAge) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.eyeColor = eyeColor;
		this.height = height;
		this.weight = weight;
		this.birthday = birthday;
		this.userAge = userAge;
	}

	/**
	 * @param userName
	 * @param eyeColor
	 * @param height
	 * @param weight
	 * @param birthday
	 * @param userAge
	 */
	public User(String userName, String eyeColor, Integer height, Integer weight, Date birthday, Integer userAge) {
		super();
		this.userName = userName;
		this.eyeColor = eyeColor;
		this.height = height;
		this.weight = weight;
		this.birthday = birthday;
		this.userAge = userAge;
	}

	/**
	 * @return the userOrderList
	 */
	public List<Orders> getUserOrderList() {
		return userOrderList;
	}

	/**
	 * @param userOrderList the userOrderList to set
	 */
	public void setUserOrderList(List<Orders> userOrderList) {
		this.userOrderList = userOrderList;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @return the eyeColor
	 */
	public String getEyeColor() {
		return eyeColor;
	}

	/**
	 * @return the height
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 * @return the userAge
	 */
	public Integer getUserAge() {
		return userAge;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the weight
	 */
	public Integer getWeight() {
		return weight;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @param eyeColor
	 *            the eyeColor to set
	 */
	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/**
	 * @param userAge
	 *            the userAge to set
	 */
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	@JsonCreator
	public void setUserId(@JsonProperty("userId") Integer userId) {
		this.userId = userId;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", eyeColor=" + eyeColor + ", height=" + height
				+ ", weight=" + weight + ", birthday=" + birthday + ", userAge=" + userAge + "]";
	}

}
