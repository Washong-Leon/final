package com.clin.entity;

import javax.websocket.Session;

public class OnlineUser {

	private Session session;
	public OnlineUser() {}
	public OnlineUser(Session session) {
		this.session=session;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	
}
