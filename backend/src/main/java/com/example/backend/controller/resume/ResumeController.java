package com.example.backend.controller.resume;

import com.example.backend.service.resume.FindResumeByYearMonth;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/resumes")
public class ResumeController {

    private final FindResumeByYearMonth findResumeByYearMonth;

    public ResumeController(FindResumeByYearMonth findResumeByYearMonth) {
        this.findResumeByYearMonth = findResumeByYearMonth;
    }

    @GetMapping("/{year}/{month}")
    public ResponseEntity<ResumeDTO> findResumeByYearMonth(Principal principal, @PathVariable Integer year, @PathVariable Integer month) {
        return ResponseEntity.ok(this.findResumeByYearMonth.execute(principal, year, month));
    }
}
