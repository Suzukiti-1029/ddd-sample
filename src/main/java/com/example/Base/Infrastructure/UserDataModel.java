package com.example.base.infrastructure;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")

@NoArgsConstructor
public class UserDataModel implements Serializable {
	@Id
	private String id;

	@Column(nullable = false)
	private String name;
}
