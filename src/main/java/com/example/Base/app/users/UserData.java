package com.example.base.app.users;

import com.example.base.domain.entity.User;

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
