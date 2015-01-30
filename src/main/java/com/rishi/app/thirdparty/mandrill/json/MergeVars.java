package com.rishi.app.thirdparty.mandrill.json;

public class MergeVars {
	private Vars[] vars;

	private String rcpt;

	public Vars[] getVars() {
		return vars;
	}

	public void setVars(Vars[] vars) {
		this.vars = vars;
	}

	public String getRcpt() {
		return rcpt;
	}

	public void setRcpt(String rcpt) {
		this.rcpt = rcpt;
	}
}
