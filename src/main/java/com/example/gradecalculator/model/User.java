package com.example.gradecalculator.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users") 
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // A User can have many GradeRecords
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<GradeRecord> gradeRecords = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<GradeRecord> getGradeRecords() {
		return gradeRecords;
	}

	public void setGradeRecords(List<GradeRecord> gradeRecords) {
		this.gradeRecords = gradeRecords;
	}

   
}