package com.achieveit.application.controller;

import com.achieveit.application.entity.RiskEntity;
import com.achieveit.application.service.RiskService;
import com.achieveit.application.wrapper.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/risk/")
public class RiskController {
    private final RiskService riskService;

    @Autowired
    public RiskController(RiskService riskService){
        this.riskService=riskService;
    }

    @CrossOrigin
    @GetMapping("getAllRisks")
    ResponseResult<ArrayList<RiskEntity>> getAllRisks(HttpSession session){
        return riskService.getAllRisks(session);
    }

    @CrossOrigin
    @PostMapping("addRisk")
    ResponseResult<Boolean> addRisk(@RequestParam("riskDescription")String riskDescription,@RequestParam("riskType") int riskType,
                                    @RequestParam("riskCharger") String riskCharger,@RequestParam("riskLevel") int riskLevel,
                                    @RequestParam("riskInfluence")int riskInfluence,@RequestParam("riskStatus") int riskStatus, HttpSession session){
        return riskService.addRisk(riskDescription,riskType,riskCharger,riskLevel,riskInfluence,riskStatus,session);
    }

    @CrossOrigin
    @PostMapping("deleteRisk")
    ResponseResult<Boolean> deleteRisk(@RequestParam("riskID")int riskId){
        return riskService.deleteRisk(riskId);
    }

}
