package com.achieveit.application.controller;

import com.achieveit.application.entity.RiskEntity;
import com.achieveit.application.entity.RiskTemplate;
import com.achieveit.application.service.RiskService;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RestController
@RequestMapping("/risk/")
public class RiskController {
    private final RiskService riskService;

    @Autowired
    public RiskController(RiskService riskService){
        this.riskService=riskService;
    }

    @CrossOrigin
    @GetMapping("getAllRisks")
    public ResponseResult<ArrayList<RiskEntity>> getAllRisks(HttpSession session){
        return riskService.getAllRisks(session);
    }

    @CrossOrigin
    @PostMapping("addRisk")
    public ResponseResult<RiskEntity> addRisk(@RequestParam("riskDescription")String riskDescription,
                                              @RequestParam("riskType") int riskType,
                                              @RequestParam("riskCharger") String riskCharger,
                                              @RequestParam("riskLevel") int riskLevel,
                                              @RequestParam("riskInfluence")int riskInfluence,
                                              @RequestParam("riskFrequency")int riskFrequency,
                                              @RequestParam(value="riskStrategy",required = false,defaultValue = "")String riskStrategy,
                                              @RequestParam(value = "riskStatus",required = false,defaultValue = "0") int riskStatus,
                                              @RequestParam("projectID")String projectID,
                                              @RequestParam("riskHolders[]")String[] riskHolders,
                                              HttpSession session){
        return riskService.addRisk(riskDescription,riskType,riskCharger,riskLevel,riskInfluence,riskFrequency,riskStrategy,riskStatus,projectID,riskHolders,session);
    }

    @CrossOrigin
    @PostMapping("updateRiskByRiskID")
    public ResponseResult<RiskEntity> updateRiskByRiskID(@RequestParam("riskID")String riskId,
                                                      @RequestParam(value = "riskDescription",required = false,defaultValue = "")String riskDescription,
                                                      @RequestParam(value="riskInfluence",required = false,defaultValue = "-1")int riskInfluence,
                                                      @RequestParam(value = "riskType",required = false,defaultValue = "-1") int riskType,
                                                      @RequestParam(value = "riskLevel",required = false,defaultValue = "-1") int riskLevel,
                                                      @RequestParam(value = "riskFrequency",required = false,defaultValue = "-1")int riskFrequency,
                                                      @RequestParam(value = "riskCharger",required = false,defaultValue = "-1") String riskCharger,
                                                      @RequestParam(value="riskHolders",required = false,defaultValue = "")String riskHolders,
                                                      @RequestParam(value="riskStrategy",defaultValue = "",required = false)String riskStrategy){
        String[] riskHolderList=new String[0];
        if(!riskHolders.equals("")){
            riskHolderList =riskHolders.split(",");
        }

        return riskService.updateRiskByRiskID(riskId,riskDescription,riskInfluence,riskType,riskLevel,riskFrequency,riskCharger,riskHolderList,riskStrategy);
    }

    @CrossOrigin
    @PostMapping("deleteRisk")
    public ResponseResult<Boolean> deleteRisk(@RequestParam("riskID")int riskId){
        return riskService.deleteRisk(riskId);
    }

    @CrossOrigin
    @PostMapping("changeRiskStatus")
    public ResponseResult<Boolean> changeRiskStatus(@RequestParam("riskID")int riskId,@RequestParam("riskCharger")String riskCharger,HttpSession session){
        return riskService.changeRiskStatus(riskId,riskCharger);
    }

    @CrossOrigin
    @GetMapping("getRiskHoldersByRiskID")
    public ResponseResult<ArrayList<String>> getRiskHoldersByRiskId(@RequestParam("riskID")int riskId){
        return riskService.getRiskHoldersByRiskId(riskId);
    }

    @CrossOrigin
    @PostMapping("addRiskHoldersByRiskID")
    public ResponseResult<Boolean> addRiskHoldersByRiskId(@RequestParam("riskID")int riskId,@RequestParam("riskHolder")String riskHolder){
        return riskService.addRiskHoldersByRiskId(riskId,riskHolder);
    }

    @CrossOrigin
    @PostMapping("addRiskTemplate")
    public ResponseResult<Boolean> addRiskTemplate(@RequestParam(value = "riskDescription",defaultValue = "",required = false)String riskDescription,@RequestParam("riskType")int riskType,
                                                   @RequestParam("riskLevel")int riskLevel,@RequestParam("riskInfluence")int riskInfluence,
                                                   @RequestParam("riskStrategy")String riskStrategy){
        return riskService.addRiskTemplate(riskDescription,riskType,riskLevel,riskInfluence,riskStrategy);
    }

    @CrossOrigin
    @GetMapping("getRiskTemplates")
    public ResponseResult<ArrayList<RiskTemplate>> getRiskTemplates(){
        return riskService.getRiskTemplates();
    }

    @CrossOrigin
    @GetMapping("getRiskTemplatesByProjectID")
    public ResponseResult<ArrayList<RiskTemplate>> getRiskTemplatesByProjectID(@RequestParam("projectID")String projectID) {
        return riskService.getRiskTemplatesByProjectID(projectID);
    }

    @CrossOrigin
    @GetMapping("getRisksByProjectID")
    public ResponseResult<ArrayList<RiskEntity>> getRisksByProjectID(@RequestParam("projectID")String projectID) {
        return riskService.getRisksByProjectID(projectID);
    }

    @CrossOrigin
    @PostMapping("solveRisk")
    public ResponseResult<Boolean> solveRisk(@RequestParam("riskID")String riskId){
        return riskService.solveRisk(riskId);
    }

}
