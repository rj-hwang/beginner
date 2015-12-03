package com.beginner.jpa;

import javax.persistence.*;

@Entity
@Table(name = "T_USER")
public class User {
	private Integer id;
	private String name;

	public User(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}