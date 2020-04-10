package com.achieveit.application.controller;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.exception.AchieveitException;
import com.achieveit.application.service.StatusService;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @CrossOrigin
    @Logged({"projectID"})
    @PostMapping("/approveApplication")
    public ResponseResult approveApplication(@RequestParam("projectID") String projectID) throws AchieveitException {
        statusService.approveApplication(projectID);
        return ResultGenerator.success();
    }

    @CrossOrigin
    @Logged({"projectID"})
    @PostMapping("/rejectApplication")
    public ResponseResult rejectApplication(@RequestParam("projectID") String projectID) throws AchieveitException {
        statusService.rejectApplication(projectID);
        return ResultGenerator.success();
    }

    @CrossOrigin
    @Logged({"projectID"})
    @PostMapping("/launchProject")
    public ResponseResult launchProject(@RequestParam("projectID") String projectID) throws AchieveitException {
        statusService.launchProject(projectID);
        return ResultGenerator.success();
    }

    @CrossOrigin
    @Logged({"projectID"})
    @PostMapping("/deliverProject")
    public ResponseResult deliverProject(@RequestParam("projectID") String projectID) throws AchieveitException {
        statusService.deliverProject(projectID);
        return ResultGenerator.success();
    }

    @CrossOrigin
    @Logged({"projectID"})
    @PostMapping("/endProject")
    public ResponseResult endProject(@RequestParam("projectID") String projectID) throws AchieveitException {
        statusService.endProject(projectID);
        return ResultGenerator.success();
    }

    @CrossOrigin
    @Logged({"projectID", "archiveLink"})
    @PostMapping("/updateArchive")
    public ResponseResult updateArchive(@RequestParam("projectID") String projectID, @RequestParam("archiveLink") String archiveLink) throws AchieveitException {
        statusService.updateArchive(projectID, archiveLink);
        return ResultGenerator.success();
    }

    @CrossOrigin
    @Logged({"projectID"})
    @PostMapping("/rejectArchive")
    public ResponseResult rejectArchive(@RequestParam("projectID") String projectID) throws AchieveitException {
        statusService.rejectArchive(projectID);
        return ResultGenerator.success();
    }

    @CrossOrigin
    @Logged({"projectID"})
    @PostMapping("/approveArchive")
    public ResponseResult approveArchive(@RequestParam("projectID") String projectID) throws AchieveitException {
        statusService.approveArchive(projectID);
        return ResultGenerator.success();
    }
}
