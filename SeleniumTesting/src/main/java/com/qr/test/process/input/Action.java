package com.qr.test.process.input;

import java.io.Serializable;

import com.qr.test.process.enums.ActionType;
import com.qr.test.process.enums.AttributeType;

public class Action implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5446417888984236439L;

	private AttributeType findByAttbuteType;

	private String id;

	private String name;

	private String linkText;

	private String cssClass;

	private String xPath;

	private String value;

	private String description;

	private ActionType actionType;

	public Action() {
		// TODO Auto-generated constructor stub
	}

	public AttributeType getFindByAttbuteType() {
		return findByAttbuteType;
	}

	public void setFindByAttbuteType(AttributeType findByAttbuteType) {
		this.findByAttbuteType = findByAttbuteType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLinkText() {
		return linkText;
	}

	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getxPath() {
		return xPath;
	}

	public void setxPath(String xPath) {
		this.xPath = xPath;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
