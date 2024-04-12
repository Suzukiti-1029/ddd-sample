package com.example.Base.Domain.ValueObject;

import java.util.Objects;

import lombok.Getter;

@Getter
public class UserId {
	public UserId(String val) throws Exception {
		if (Objects.equals(val, null))
			throw new Exception("UserId: value must not be null.");
		value = val;
	}

	private String value;
}
