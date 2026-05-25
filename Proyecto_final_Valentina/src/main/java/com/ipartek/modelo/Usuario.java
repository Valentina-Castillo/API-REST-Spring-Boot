package com.ipartek.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true, length = 45)
	private String user;
	
	@Column(nullable = false, length = 64)
	private String pass;
	
	@Column(nullable = false, length = 45)
	private String role;
	
	@Column(nullable = false, length = 64)
	private String salt;

	public Usuario(int id, String user, String pass, String role, String salt) {
		super();
		this.id = id;
		this.user = user;
		this.pass = pass;
		this.role = role;
		this.salt = salt;
	}
	
	public Usuario() {
		super();
		this.id = 0;
		this.user = "";
		this.pass = "";
		this.role = "";
		this.salt = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", user=" + user + ", pass=" + pass + ", role=" + role + ", salt=" + salt + "]";
	}
	
	
	
	
	

}
