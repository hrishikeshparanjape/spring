package com.rishi.app.thirdparty.mandrill.json;

import org.codehaus.jackson.annotate.JsonProperty;

public class MandrillMessagePayload {
	private Message message;

	@JsonProperty("send_at")
	private String sendAt;

	@JsonProperty("ip_pool")
	private String ipPool;

	private String async;

	private String key;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getSendAt() {
		return sendAt;
	}

	public void setSendAt(String sendAt) {
		this.sendAt = sendAt;
	}

	public String getIpPool() {
		return ipPool;
	}

	public void setIpPool(String ipPool) {
		this.ipPool = ipPool;
	}

	public String getAsync() {
		return async;
	}

	public void setAsync(String async) {
		this.async = async;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
