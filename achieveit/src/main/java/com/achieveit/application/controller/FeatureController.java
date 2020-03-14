package com.achieveit.application.controller;

import com.achieveit.application.entity.FeatureEntity;
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

    @PostMapping("addTopFeature")
    public ResponseResult<Boolean> addTopFeature(@RequestParam(name = "featurename")String featureName,@RequestParam(name = "projectid") String projectId, HttpSession session){
        return featureService.insertTopFeature(featureName,projectId,session);
    }

    @PostMapping("addSubFeature")
    public ResponseResult<Boolean> addSubFeature(@RequestParam(name = "featurename")String featureName,@RequestParam(name = "projectid")String projectId,
                                                 @RequestParam(name = "fatherid")String fatherId,HttpSession session){
        return featureService.insertSubFeature(featureName,projectId,fatherId,session);
    }

}
