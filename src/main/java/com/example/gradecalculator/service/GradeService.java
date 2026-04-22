package com.example.gradecalculator.service;

import org.springframework.stereotype.Service;

import com.example.gradecalculator.model.GradeRecord;
import com.example.gradecalculator.repository.GradeRecordRepository;

@Service
public class GradeService {

	private final GradeRecordRepository repository;

	public GradeService(GradeRecordRepository repository) {
		this.repository = repository;
	}

	// Update this method signature and add record.setUser(user);
    public void processAndSaveGrade(GradeRecord record, com.example.gradecalculator.model.User user) {
        record.setUser(user); // Link calculation to logged in user
        
        double total = 0;
        int numberOfSubjects = record.getSubjects().size();

        for (com.example.gradecalculator.model.SubjectGrade subject : record.getSubjects()) {
            total += subject.getScore();
            subject.setGradeRecord(record); 
        }

        double totalMaxMarks = numberOfSubjects * record.getOutOf();
        double percentage = 0;
        if (totalMaxMarks > 0) percentage = (total / totalMaxMarks) * 100.0;

        record.setTotalScore(total);
        record.setPercentage(percentage);
        record.setGrade(determineGrade(percentage));

        repository.save(record);
    }

	private String determineGrade(double percentage) {
		if (percentage >= 90)
			return "O";
		else if (percentage >= 80)
			return "A";
		else if (percentage >= 70)
			return "B";
		else if (percentage >= 60)
			return "C";
		else if (percentage >= 40)
			return "D";
		else
			return "F";
	}
}