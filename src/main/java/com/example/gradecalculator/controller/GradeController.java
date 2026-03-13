package com.example.gradecalculator.controller;

import com.example.gradecalculator.model.GradeRecord;
import com.example.gradecalculator.model.SubjectGrade;
import com.example.gradecalculator.service.GradeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GradeController {

	private final GradeService gradeService;

	public GradeController(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	@GetMapping("/")
	public String showForm(Model model) {
		GradeRecord record = new GradeRecord();
		record.getSubjects().add(new SubjectGrade());
		model.addAttribute("gradeRecord", record);
		return "index";
	}

	@PostMapping(value = "/", params = { "addRow" })
	public String addSubjectRow(@ModelAttribute("gradeRecord") GradeRecord gradeRecord) {
		gradeRecord.getSubjects().add(new SubjectGrade());
		return "index";
	}

	@PostMapping(value = "/", params = { "calculate" })
	public String calculateGrade(@ModelAttribute("gradeRecord") GradeRecord gradeRecord, Model model) {
		gradeService.processAndSaveGrade(gradeRecord);
		model.addAttribute("record", gradeRecord);
		return "result";
	}
}