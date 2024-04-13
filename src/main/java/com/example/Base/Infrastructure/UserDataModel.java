package com.example.Base.Infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserDataModel {
	@Id
	private String id;

	@Column(nullable = false)
	private String name;
}
