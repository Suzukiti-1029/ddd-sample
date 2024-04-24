package com.example.base.domain.ValueObject;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

@Getter
public class UserId {
	public UserId(String val) throws Exception {
		if (StringUtils.isBlank(val))
			throw new Exception("UserId: value must not be blank.");
		value = val;
	}

	private String value;
}
