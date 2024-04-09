package com.example.Circle.Entity;

import java.util.Objects;

import com.example.Circle.ValueObject.CircleId;
import com.example.Circle.ValueObject.CircleName;

import lombok.Getter;

@Getter
public class Circle {
	public Circle(CircleId cId, CircleName cName) throws Exception {
		if (Objects.equals(cId, null))
			throw new Exception("Circle Id must not be null.");
		if (Objects.equals(cName, null))
			throw new Exception("Circle Name must not be null.");
		// if (Objects.equals(o, null))
		// throw new Exception("Owner must not be null.");
		// if (Objects.equals(m, null))
		// throw new Exception("Members must not be null.");
		circleId = cId;
		circleName = cName;
		// owner = o;
		// members = m;
	}

	private CircleId circleId;
	private CircleName circleName;
	// private User owner;
	// private List<User> members;
}
