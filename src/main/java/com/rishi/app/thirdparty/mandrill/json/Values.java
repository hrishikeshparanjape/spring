package com.rishi.app.thirdparty.mandrill.json;

import org.codehaus.jackson.annotate.JsonProperty;

public class Values {
	@JsonProperty("user_id")
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
