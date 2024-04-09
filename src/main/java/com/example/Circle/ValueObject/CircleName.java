package com.example.Circle.ValueObject;

import java.util.Objects;

import lombok.Getter;

@Getter
public class CircleName {
	public CircleName(String v) throws Exception {
		if (Objects.equals(v, null))
			throw new Exception("Circle Name's value must not be null.");
		if (v.length() < 3)
			throw new Exception("サークル名は3文字以上です。");
		if (v.length() > 20)
			throw new Exception("サークル名は20文字以下です。");
		value = v;
	}

	private String value;

	public boolean equals(CircleName other) {
		if (Objects.equals(null, other))
			return false;
		if (Objects.equals(this, other))
			return true;
		return Objects.equals(value, other.value);
	}

	@Override
	public boolean equals(Object obj) {
		if (Objects.equals(null, obj))
			return false;
		if (Objects.equals(this, obj))
			return true;
		if (!Objects.equals(this.getClass(), obj.getClass()))
			return false;
		return Objects.equals(this, (CircleName) obj);
	}
}
