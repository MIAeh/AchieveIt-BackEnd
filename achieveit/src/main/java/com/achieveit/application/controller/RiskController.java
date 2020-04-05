package com.achieveit.application.controller;

import com.achieveit.application.entity.RiskEntity;
import com.achieveit.application.entity.RiskTemplate;
import com.achieveit.application.service.RiskService;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
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
    public ResponseResult<RiskEntity> addRisk(@RequestParam("riskDescription")String riskDescription,@RequestParam("riskType") int riskType,
                                    @RequestParam("riskCharger") String riskCharger,@RequestParam("riskLevel") int riskLevel,
                                    @RequestParam("riskInfluence")int riskInfluence,@RequestParam("riskFrequency")int riskFrequency,
                                              @RequestParam(value="riskStrategy",defaultValue = "",required = false)String riskStrategy,
                                              @RequestParam(value = "riskStatus",required = false,defaultValue = "0") int riskStatus, HttpSession session){
        return riskService.addRisk(riskDescription,riskType,riskCharger,riskLevel,riskInfluence,riskFrequency,riskStrategy,riskStatus,session);
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
    public ResponseResult<Boolean> addRiskTemplate(@RequestParam("riskDescription")String riskDescription,@RequestParam("riskType")int riskType,
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
    @GetMapping("getRiskTemplateByProjectID")
    public ResponseResult<ArrayList<RiskEntity>> getRiskTemplateByProjectID(@RequestParam("projectID")String projectID) {
        return null;
    }

}
