// Update GradeRecordRepository.java
package com.example.gradecalculator.repository;
import com.example.gradecalculator.model.GradeRecord;
import com.example.gradecalculator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GradeRecordRepository extends JpaRepository<GradeRecord, Long> {
    // Custom method to fetch a specific user's grades, newest first
    List<GradeRecord> findByUserOrderByIdDesc(User user); 
}