package com.example.gradecalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gradecalculator.model.GradeRecord;

public interface GradeRecordRepository extends JpaRepository<GradeRecord, Long>{

}
