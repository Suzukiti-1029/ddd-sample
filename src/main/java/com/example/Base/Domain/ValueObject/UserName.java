package com.example.Base.Domain.ValueObject;

import java.util.Objects;

import lombok.Getter;

@Getter
public class UserName {
	public UserName(String val) throws Exception {
		if (Objects.equals(val, null))
			throw new Exception("UserName: value must not be null.");
		if (val.length() < 3)
			throw new Exception("ユーザ名は3文字以上です。");
		value = val;
	}

	private String value;
}
