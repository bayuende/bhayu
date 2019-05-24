package com.bhayu.app.model;

import java.util.List;

/**
 * Created by mzennis on 2/27/17.
 */
public class Base<T> {

	private List<T> data;
	private String status;
	private String message;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
