package com.achieveit.application.controller;

import com.achieveit.application.entity.FeatureEntity;
import com.achieveit.application.entity.FeatureUpLoad;
import com.achieveit.application.service.FeatureService;
import com.achieveit.application.wrapper.ResponseResult;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
    private  final FeatureService featureService;

    @Autowired
    public FeatureController(FeatureService featureService){
        this.featureService=featureService;
    }

    @GetMapping("getFeaturesInfo")
    public ResponseResult<ArrayList<FeatureEntity>> getFeatureInfo(HttpSession session){
        return featureService.getFeaturesInfo(session);
    }

    @CrossOrigin
    @GetMapping("getFeaturesByProjectID")
    public ResponseResult<ArrayList<FeatureEntity>> getFeaturesByProjectID(@RequestParam("projectID")String projectId,HttpSession session){
        return featureService.getFeaturesByProjectID(projectId);
    }

    @CrossOrigin
    @GetMapping("deleteFeatureByFeatureID")
    public ResponseResult<Boolean> deleteFeatureByFeatureID(@RequestParam("featureID")String featureId,HttpSession session){
        return featureService.deleteFeatureByFeatureID(featureId);
    }

    @CrossOrigin
    @PostMapping("addTopFeature")
    public ResponseResult<FeatureEntity> addTopFeature(@RequestParam(name = "featureName")String featureName,@RequestParam(name = "projectID") String projectId,
                                                @RequestParam(name = "featureDescription")String featureDescription, HttpSession session){
        return featureService.insertTopFeature(featureName,projectId,featureDescription,session);
    }

    @CrossOrigin
    @PostMapping("addSubFeature")
    public ResponseResult<FeatureEntity> addSubFeature(@RequestParam(name = "featureName")String featureName,@RequestParam(name = "projectID")String projectId,
                                                 @RequestParam(name = "fatherID")String fatherId,@RequestParam(name = "featureDescription")String featureDescription,HttpSession session){
        return featureService.insertSubFeature(featureName,projectId,fatherId,featureDescription,session);
    }

    @CrossOrigin
    @PostMapping(value="uploadFeatureList",produces = "application/json;charset=UTF-8")
    public ResponseResult<Boolean> uploadFeatureList(@RequestBody FeatureUpLoad featureUpLoad,HttpSession session){
        return featureService.uploadFeatureList(featureUpLoad,session);
    }

}
