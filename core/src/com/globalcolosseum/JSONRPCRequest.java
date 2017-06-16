package com.globalcolosseum;

public class JSONRPCRequest {
	private String method;
	private String[] params;
	private int id;
	
	public JSONRPCRequest(String method, String[] params, int id) {
		this.method = method;
		this.params = params;
		this.id = id;
	}

	public String getMethod() {
		return method;
	}
	
	public String[] getParameters() {
		return params;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
}
