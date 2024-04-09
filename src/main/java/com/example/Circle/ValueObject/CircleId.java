package com.example.Circle.ValueObject;

import java.util.Objects;

import lombok.Getter;

@Getter
public class CircleId {
	public CircleId(String v) throws Exception {
		if (Objects.equals(v, null))
			throw new Exception("Circle ID's value must not be null.");
		value = v;
	}

	private String value;
}
