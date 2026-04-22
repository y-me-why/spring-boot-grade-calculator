package com.example.gradecalculator.controller;

import com.example.gradecalculator.model.GradeRecord;
import com.example.gradecalculator.model.SubjectGrade;
import com.example.gradecalculator.model.User;
import com.example.gradecalculator.repository.UserRepository;
import com.example.gradecalculator.repository.GradeRecordRepository;
import com.example.gradecalculator.service.GradeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class GradeController {

    private final GradeService gradeService;
    private final UserRepository userRepository;
    private final GradeRecordRepository gradeRecordRepository;

    public GradeController(GradeService gradeService, UserRepository userRepository, GradeRecordRepository gradeRecordRepository) {
        this.gradeService = gradeService;
        this.userRepository = userRepository;
        this.gradeRecordRepository = gradeRecordRepository;
    }

    // 1. The Dashboard (Shows past grades)
    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName());
        List<GradeRecord> pastRecords = gradeRecordRepository.findByUserOrderByIdDesc(user);
        
        model.addAttribute("username", user.getUsername());
        model.addAttribute("records", pastRecords);
        return "dashboard";
    }

    // 2. The Calculator Form
    @GetMapping("/calculator")
    public String showForm(Model model) {
        GradeRecord record = new GradeRecord();
        record.getSubjects().add(new SubjectGrade()); 
        model.addAttribute("gradeRecord", record);
        return "calculator"; // We renamed index.html to calculator.html
    }

    // 3. Add row button
    @PostMapping(value = "/calculator", params = {"addRow"})
    public String addSubjectRow(@ModelAttribute("gradeRecord") GradeRecord gradeRecord) {
        gradeRecord.getSubjects().add(new SubjectGrade());
        return "calculator"; 
    }

    // 4. Calculate and Save
    @PostMapping(value = "/calculator", params = {"calculate"})
    public String calculateGrade(@ModelAttribute("gradeRecord") GradeRecord gradeRecord, Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName());
        
        gradeService.processAndSaveGrade(gradeRecord, user); // Now passing the user!
        model.addAttribute("record", gradeRecord);
        return "result"; 
    }
}