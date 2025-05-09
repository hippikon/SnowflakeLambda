package cafe.snowflake.app2.service;

import cafe.snowflake.app2.jpa.FulltimeJobApplicationDetail;
import cafe.snowflake.app2.jpa.FulltimeJobDetail;
import cafe.snowflake.app2.jpa.JobApplicationDetail;
import cafe.snowflake.app2.jpa.JobDetail;
import cafe.snowflake.app2.model.AppData;
import cafe.snowflake.app2.model.AppDataFulltime;
import cafe.snowflake.app2.model.AppDetail;
import cafe.snowflake.app2.model.AppDetailFulltime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationDetail jobAppRepository;

    @Autowired
    private FulltimeJobApplicationDetail fulltimeJobAppRepository;

    @Autowired
    private JobDetail jobDetailRepository;

    @Autowired
    private FulltimeJobDetail fulltimeJobDetailRepository;

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


    public List<AppDataFulltime> createFulltimeApplications(List<AppDataFulltime> jobApps) {

        List<AppDataFulltime> savedOutput = new ArrayList<>();
        for (AppDataFulltime application:jobApps) {
            AppDataFulltime appDataFulltime = fulltimeJobAppRepository.save(application);
            List<AppDetailFulltime> appDetailList = createApplicationDetailFulltime(appDataFulltime);
            appDataFulltime.setTagList(appDetailList);
            savedOutput.add(appDataFulltime);
        }
        return savedOutput;
    }

    public List<AppDetailFulltime> createApplicationDetailFulltime(AppDataFulltime jobApp) {
        List<AppDetailFulltime> detail = new ArrayList<>();
        for (AppDetailFulltime data:jobApp.getTagList()) {
            data.setJobId(jobApp.getId());
            detail.add(fulltimeJobDetailRepository.save(data));
        }
        return detail;
    }
}
