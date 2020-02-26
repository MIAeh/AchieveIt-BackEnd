package com.achieveit.application.controller;

import com.achieveit.application.utils.BaseJson;
import com.achieveit.application.entity.Plat;
import com.achieveit.application.service.PlatRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plat")
public class PlatController {

    @Autowired
    private PlatRouteService platRouteService;

    public PlatController(PlatRouteService platRouteService) {
        this.platRouteService = platRouteService;
    }

    @GetMapping("/query")
    public BaseJson getPlatByMapId(@RequestParam("mapId") Integer mapId) {

        BaseJson baseJson = platRouteService.getPlatByMapId( mapId );
        return baseJson;
    }

    @PostMapping("/add")
    public BaseJson addPlat(Plat plat) {

        BaseJson baseJson = platRouteService.addPlat( plat );

        return baseJson;
    }

    @PostMapping("/update")
    public BaseJson updatePlat(Plat plat) {

        BaseJson baseJson = platRouteService.updatePlat( plat );
        return baseJson;

    }


}
