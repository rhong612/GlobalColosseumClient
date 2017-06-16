package com.globalcolosseum;

public class JSONRPCResponse {
	private Object result;
	private String error;
	private int id;
	
	public JSONRPCResponse() {
		//Required to deserialize from server.
	}
	
	public JSONRPCResponse(Object result, String error, int id) {
		this.result = result;
		this.error = error;
		this.id = id;
	}

	public Object getResult() {
		return result;
	}
	
	public String getError() {
		return error;
	}
	
	public int getID() {
		return id;
	}
}
