package cafe.snowflake.app2.controllers;

import cafe.snowflake.app2.jpa.FulltimeJobApplicationDetail;
import cafe.snowflake.app2.jpa.JobApplicationDetail;
import cafe.snowflake.app2.model.AppData;
import cafe.snowflake.app2.model.AppDataFulltime;
import cafe.snowflake.app2.service.JobApplicationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.List;

@RestController
public class RestJobController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @Autowired
    private FulltimeJobApplicationDetail fulltimeJobApplicationDetail;

    @Autowired
    private JobApplicationDetail jobApplicationDetail;

    private ObjectMapper objectMapper = new ObjectMapper();


    @PostMapping("/rest/create/job")
    public String rest(@RequestBody AppData appData) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().with(DateFormat.getDateTimeInstance())
                .writeValueAsString(jobApplicationService.createApplications(List.of(appData)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/rest/create/fulltime/app")
    public String fulltimeJobApplication(@RequestBody AppDataFulltime appData) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().with(DateFormat.getDateTimeInstance())
                .writeValueAsString(jobApplicationService.createFulltimeApplications(List.of(appData)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/rest/fulltime/view")
    public List<AppDataFulltime> viewFulltimeJobApplication() {
        return fulltimeJobApplicationDetail.findAll();
    }

    @GetMapping("/rest/contract/view")
    public List<AppData> viewContractJobApplications() {
        return jobApplicationDetail.findAll();
    }

    @PostMapping("/rest/update/fulltime/applied")
    public String fulltimeJobApplicationApplied(@RequestBody List<AppDataFulltime> applied) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().with(DateFormat.getDateTimeInstance())
                .writeValueAsString(jobApplicationService.updateFulltimeApplications(applied));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
