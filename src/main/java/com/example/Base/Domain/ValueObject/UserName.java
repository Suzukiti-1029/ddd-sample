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
		if (val.length() > 20)
			throw new Exception("ユーザ名は20文字以下です。");
		value = val;
	}

	private String value;
}
