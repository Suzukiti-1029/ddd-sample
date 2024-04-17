package com.example.Base.app.users;

import com.example.Base.Domain.Entity.User;

import lombok.Getter;

@Getter
public class UserData {
	public UserData(User source) {
		this.id = source.getId().getValue();
		this.name = source.getName().getValue();
	}

	private String id;
	private String name;
}
