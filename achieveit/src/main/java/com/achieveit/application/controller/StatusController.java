package com.achieveit.application.controller;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.annotation.PostControl;
import com.achieveit.application.entity.ArchiveEntity;
import com.achieveit.application.entity.ProjectStatusEntity;
import com.achieveit.application.enums.ProjectStatus;
import com.achieveit.application.exception.AchieveitException;
import com.achieveit.application.service.StatusService;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/status")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @CrossOrigin
    @Logged({"projectID"})
    @GetMapping("/getProjectStatus")
    public ResponseResult<ProjectStatusEntity> getProjectStatus(@RequestParam("projectID") String projectID) throws AchieveitException {
        ProjectStatusEntity projectStatus = statusService.getProjectStatus(projectID);
        return ResultGenerator.success(projectStatus);
    }

    @PostControl
    @CrossOrigin
    @Logged({"projectID"})
    @PostMapping("/approveApplication")
    public ResponseResult approveApplication(@RequestParam("projectID") String projectID, HttpSession session) throws AchieveitException {
        statusService.approveApplication(projectID, session);
        return ResultGenerator.success();
    }

    @PostControl
    @CrossOrigin
    @Logged({"projectID"})
    @PostMapping("/rejectApplication")
    public ResponseResult rejectApplication(@RequestParam("projectID") String projectID, HttpSession session) throws AchieveitException {
        statusService.rejectApplication(projectID, session);
        return ResultGenerator.success();
    }

    @PostControl
    @CrossOrigin
    @Logged({"projectID"})
    @PostMapping("/confirmConfigurationCompleted")
    public ResponseResult confirmConfigurationCompleted(@RequestParam("projectID") String projectID) throws AchieveitException {
        statusService.confirmConfigurationCompleted(projectID);
        return ResultGenerator.success();
    }

    @PostControl
    @CrossOrigin
    @Logged({"projectID"})
    @PostMapping("/launchProject")
    public ResponseResult launchProject(@RequestParam("projectID") String projectID) throws AchieveitException {
        statusService.launchProject(projectID);
        return ResultGenerator.success();
    }

    @PostControl
    @CrossOrigin
    @Logged({"projectID"})
    @PostMapping("/deliverProject")
    public ResponseResult deliverProject(@RequestParam("projectID") String projectID) throws AchieveitException {
        statusService.deliverProject(projectID);
        return ResultGenerator.success();
    }

    @PostControl
    @CrossOrigin
    @Logged({"projectID"})
    @PostMapping("/endProject")
    public ResponseResult endProject(@RequestParam("projectID") String projectID) throws AchieveitException {
        statusService.endProject(projectID);
        return ResultGenerator.success();
    }

    @PostControl
    @CrossOrigin
    @Logged({"projectID", "archiveLink"})
    @PostMapping("/updateArchive")
    public ResponseResult updateArchive(@RequestParam("projectID") String projectID, @RequestParam("archiveLink") String archiveLink) throws AchieveitException {
        statusService.updateArchive(projectID, archiveLink);
        return ResultGenerator.success();
    }

    @CrossOrigin
    @Logged({"projectID"})
    @GetMapping("/getArchiveLink")
    public ResponseResult getArchiveLink(@RequestParam("projectID") String projectID) throws AchieveitException {
        ArchiveEntity archiveEntity = statusService.getArchiveLink(projectID);
        return ResultGenerator.success(archiveEntity);
    }

    @PostControl
    @CrossOrigin
    @Logged({"projectID"})
    @PostMapping("/rejectArchive")
    public ResponseResult rejectArchive(@RequestParam("projectID") String projectID) throws AchieveitException {
        statusService.rejectArchive(projectID);
        return ResultGenerator.success();
    }

    @PostControl
    @CrossOrigin
    @Logged({"projectID"})
    @PostMapping("/approveArchive")
    public ResponseResult approveArchive(@RequestParam("projectID") String projectID) throws AchieveitException {
        statusService.approveArchive(projectID);
        return ResultGenerator.success();
    }
}
