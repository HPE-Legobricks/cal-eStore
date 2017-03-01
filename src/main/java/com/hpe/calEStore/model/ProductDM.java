/**
 * 
 */
package com.hpe.calEStore.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author sangaras
 *
 */
public class ProductDM {

	/**
	 * 
	 */
	public ProductDM() {
		// TODO Auto-generated constructor stub
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public float getMsrpPerUnit() {
		return msrpPerUnit;
	}

	public void setMsrpPerUnit(float msrpPerUnit) {
		this.msrpPerUnit = msrpPerUnit;
	}

	public Float getDiscPercent() {
		return discPercent;
	}

	public void setDiscPercent(Float discPercent) {
		this.discPercent = discPercent;
	}

	public float getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(float pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getIsPublishedInd() {
		return isPublishedInd;
	}

	public void setIsPublishedInd(String isPublishedInd) {
		this.isPublishedInd = isPublishedInd;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public ProductDM[] getSelectedUserBox() {
		return selectedUserBox;
	}

	@JsonIgnore
	public void setSelectedUserBox(ProductDM[] selectedUserBox) {
		this.selectedUserBox = selectedUserBox;
	}

	public String getSelectPreference() {
		return selectPreference;
	}

	@JsonIgnore
	public void setSelectPreference(String selectPreference) {
		this.selectPreference = selectPreference;
	}

	@JsonIgnore
	public List<ProductDM> getUserCheckBoxList() {
		return userCheckBoxList;
	}

	public void setUserCheckBoxList(List<ProductDM> userCheckBoxList) {
		this.userCheckBoxList = userCheckBoxList;
	}

	private Integer productId;
	private String category;
	private String brand;
	private String productName;
	private String productDesc;
	private String productType;
	private String imgPath;
	private float msrpPerUnit;
	private Float discPercent;
	private float pricePerUnit;
	private Integer rating;
	private String isPublishedInd;
	private Date publishedDate;

	ProductDM[] selectedUserBox;

	private String selectPreference = "";

	List<ProductDM> userCheckBoxList = new ArrayList<ProductDM>();

}
