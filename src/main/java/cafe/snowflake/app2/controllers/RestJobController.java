package cafe.snowflake.app2.controllers;

import cafe.snowflake.app2.model.AppData;
import cafe.snowflake.app2.service.JobApplicationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestJobController {

    @Autowired
    private JobApplicationService jobApplicationService;

    private ObjectMapper objectMapper = new ObjectMapper();


    @PostMapping("/rest/create/job")
    public String rest(@RequestBody AppData appData) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jobApplicationService.createApplications(List.of(appData)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/rest/create/fulltime/app")
    public String fulltimeJobApplication(@RequestBody AppData appData) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jobApplicationService.createApplications(List.of(appData)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
