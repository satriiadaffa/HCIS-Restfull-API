package site.satriiadaffa.hcis.app.controller.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.satriiadaffa.hcis.app.model.management.JobHistoryModel;
import site.satriiadaffa.hcis.app.service.management.JobHistoryService;

import java.util.*;

@RestController
@RequestMapping("/api/job-histories")
public class JobHistoryController {

    @Autowired
    private JobHistoryService jobHistoryService;

    @GetMapping
    public List<JobHistoryModel> getAllJobHistories() {
        return jobHistoryService.getAllJobHistories();
    }

    @GetMapping("/{id}")
    public Optional<JobHistoryModel> getJobHistoryById(@PathVariable Long id) {
        return jobHistoryService.getJobHistoryById(id);
    }

    @PostMapping
    public JobHistoryModel createJobHistory(@RequestBody JobHistoryModel jobHistory) {
        return jobHistoryService.createJobHistory(jobHistory);
    }

    @PutMapping("/{id}")
    public JobHistoryModel updateJobHistory(@PathVariable Long id, @RequestBody JobHistoryModel jobHistory) {
        return jobHistoryService.updateJobHistory(id, jobHistory);
    }

    @DeleteMapping("/{id}")
    public void deleteJobHistory(@PathVariable Long id) {
        jobHistoryService.deleteJobHistory(id);
    }
}