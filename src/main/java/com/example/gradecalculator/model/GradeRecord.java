package com.example.gradecalculator.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class GradeRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String studentName;
	private double totalScore;
	private double percentage;
	private String grade;
	private double outOf;

	// Make sure your variable at the top of the file looks like this:
    // private double outOf;

    public double getOutOf() {
        return outOf;
    }

    public void setOutOf(double outOf) {
        this.outOf = outOf;
    }

	@OneToMany(mappedBy = "gradeRecord", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SubjectGrade> subjects = new ArrayList<>();

	// Add this right below your other variables
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
	
//    public GradeRecord() {
//    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addSubject(SubjectGrade subject) {
		subjects.add(subject);
		subject.setGradeRecord(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public List<SubjectGrade> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectGrade> subjects) {
		this.subjects = subjects;
	}

}