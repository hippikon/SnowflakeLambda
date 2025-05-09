package cafe.snowflake.app2.service;

import cafe.snowflake.app2.jpa.JobApplicationDetail;
import cafe.snowflake.app2.jpa.JobDetail;
import cafe.snowflake.app2.model.AppData;
import cafe.snowflake.app2.model.AppDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationDetail jobAppRepository;

    @Autowired
    private JobDetail jobDetailRepository;

    public List<AppData> createApplications(List<AppData> jobApps) {

        List<AppData> savedOutput = new ArrayList<>();
        for (AppData application:jobApps) {
            AppData appDetail = jobAppRepository.save(application);
            List<AppDetail> appDetailList = createApplicationDetail(appDetail);
            appDetail.setTagList(appDetailList);
            savedOutput.add(appDetail);
        }
        return savedOutput;
    }

    public List<AppDetail> createApplicationDetail(AppData jobApp) {
        List<AppDetail> detail = new ArrayList<>();
        for (AppDetail data:jobApp.getTagList()) {
            data.setJobId(jobApp.getId());
            detail.add(jobDetailRepository.save(data));
        }
        return detail;
    }
}
