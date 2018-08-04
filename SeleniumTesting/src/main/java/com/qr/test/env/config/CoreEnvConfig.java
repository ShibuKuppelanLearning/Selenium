package com.qr.test.env.config;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qr.test.env.config.enums.BrowserType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoreEnvConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2792957061226588373L;

	private BrowserType browserType;

	public CoreEnvConfig() {
		// TODO Auto-generated constructor stub
	}

	public BrowserType getBrowserType() {
		return browserType;
	}

	public void setBrowserType(BrowserType browserType) {
		this.browserType = browserType;
	}

}