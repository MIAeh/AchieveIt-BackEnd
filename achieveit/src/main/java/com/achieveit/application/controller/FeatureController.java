package com.achieveit.application.controller;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.annotation.PostControl;
import com.achieveit.application.entity.FeatureEntity;
import com.achieveit.application.entity.FeatureUpLoad;
import com.achieveit.application.service.FeatureService;
import com.achieveit.application.wrapper.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Controller for Feature
 *
 * @author Alevery, Felix
 */
@RestController
@RequestMapping("/feature/")
public class FeatureController {
    private final FeatureService featureService;

    @Autowired
    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }

    @GetMapping("getFeaturesInfo")
    @Logged({"session"})
    public ResponseResult<ArrayList<FeatureEntity>> getFeatureInfo(HttpSession session) {
        return featureService.getFeaturesInfo(session);
    }

    @CrossOrigin
    @Logged({"projectId", "session"})
    @GetMapping("getFeaturesByProjectID")
    public ResponseResult<ArrayList<FeatureEntity>> getFeaturesByProjectID(@RequestParam("projectID") String projectId, HttpSession session) {
        return featureService.getFeaturesByProjectID(projectId);
    }

    @PostControl(1)
    @Logged({"featureName", "projectId", "featureDescription", "session"})
    @CrossOrigin
    @PostMapping("addTopFeature")
    public ResponseResult<FeatureEntity> addTopFeature(@RequestParam(name = "featureName") String featureName, @RequestParam(name = "projectID") String projectId,
                                                       @RequestParam(name = "featureDescription", defaultValue = "", required = false) String featureDescription, HttpSession session) {
        return featureService.insertTopFeature(featureName, projectId, featureDescription, session);
    }

    @PostControl(1)
    @Logged({"featureName", "projectId", "fatherId", "featureDescription", "session"})
    @CrossOrigin
    @PostMapping("addSubFeature")
    public ResponseResult<FeatureEntity> addSubFeature(@RequestParam(name = "featureName") String featureName, @RequestParam(name = "projectID") String projectId,
                                                       @RequestParam(name = "fatherID") String fatherId, @RequestParam(name = "featureDescription", defaultValue = "", required = false) String featureDescription, HttpSession session) {
        return featureService.insertSubFeature(featureName, projectId, fatherId, featureDescription, session);
    }

    @PostControl(1)
    @Logged({"fatherId", "projectId"})
    @CrossOrigin
    @PostMapping("deleteFeatureByFeatureID")
    public ResponseResult<Boolean> deleteFeatureByFeatureID(@RequestParam(name = "featureID") String featureID, @RequestParam(name = "projectID") String projectID) {
        return featureService.deleteFeatureByFeatureID(featureID, projectID);
    }

    @PostControl
    @Logged({"featureUpLoad", "session"})
    @CrossOrigin
    @PostMapping(value = "uploadFeatureList", produces = "application/json;charset=UTF-8")
    public ResponseResult<Boolean> uploadFeatureList(@RequestBody FeatureUpLoad featureUpLoad, HttpSession session) {
        return featureService.uploadFeatureList(featureUpLoad, session);
    }

    @PostControl(1)
    @Logged({"featureID", "projectId", "featureName", "featureDescription"})
    @CrossOrigin
    @PostMapping("updateFeatureByFeatureID")
    public ResponseResult<FeatureEntity> updateFeatureByFeatureID(@RequestParam("featureID") String featureID,
                                                                  @RequestParam(name = "projectID") String projectId,
                                                                  @RequestParam(name = "featureName", required = false, defaultValue = "") String featureName,
                                                                  @RequestParam(name = "featureDescription", required = false, defaultValue = "") String featureDescription) {
        return featureService.updateFeatureByFeatureID(featureID, featureName, featureDescription, projectId);
    }
}
