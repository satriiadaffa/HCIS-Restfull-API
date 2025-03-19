package site.satriiadaffa.hcis.app.service.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.satriiadaffa.hcis.app.model.management.JobHistoryModel;
import site.satriiadaffa.hcis.app.repository.management.JobHistoryRepository;

import java.util.*;

@Service
public class JobHistoryService {

    @Autowired
    private JobHistoryRepository jobHistoryRepository;

    public List<JobHistoryModel> getAllJobHistories() {
        return jobHistoryRepository.findAll();
    }

    public Optional<JobHistoryModel> getJobHistoryById(Long id) {
        return jobHistoryRepository.findById(id);
    }

    public JobHistoryModel createJobHistory(JobHistoryModel jobHistory) {
        return jobHistoryRepository.save(jobHistory);
    }

    public JobHistoryModel updateJobHistory(Long id, JobHistoryModel jobHistoryDetails) {
        return jobHistoryRepository.findById(id)
                .map(jobHistory -> {
                    jobHistory.setOldPosition(jobHistoryDetails.getOldPosition());
                    jobHistory.setNewPosition(jobHistoryDetails.getNewPosition());
                    jobHistory.setOldDepartment(jobHistoryDetails.getOldDepartment());
                    jobHistory.setNewDepartment(jobHistoryDetails.getNewDepartment());
                    jobHistory.setStartDate(jobHistoryDetails.getStartDate());
                    jobHistory.setEndDate(jobHistoryDetails.getEndDate());
                    jobHistory.setReason(jobHistoryDetails.getReason());
                    return jobHistoryRepository.save(jobHistory);
                })
                .orElseThrow(() -> new RuntimeException("Job History not found"));
    }

    public void deleteJobHistory(Long id) {
        jobHistoryRepository.deleteById(id);
    }
}
