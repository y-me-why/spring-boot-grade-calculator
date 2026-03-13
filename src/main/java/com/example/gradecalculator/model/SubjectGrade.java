package com.example.gradecalculator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SubjectGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String subjectName; 
    private double score;       

    @ManyToOne
    @JoinColumn(name = "grade_record_id")
    private GradeRecord gradeRecord;

    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public GradeRecord getGradeRecord() {
		return gradeRecord;
	}

	public void setGradeRecord(GradeRecord gradeRecord) {
		this.gradeRecord = gradeRecord;
	}

//    public SubjectGrade() {
//    }

  
}