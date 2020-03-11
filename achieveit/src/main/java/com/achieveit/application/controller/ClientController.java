package com.achieveit.application.controller;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.entity.ClientInfo;
import com.achieveit.application.service.ClientService;
import com.achieveit.application.wrapper.ResponseResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * 根据客户ID获取客户信息
     * @param clientID 客户ID
     * @return Result
     */
    @CrossOrigin
    @Logged({"clientID"})
    @GetMapping("/getClientInfoByID")
    public ResponseResult<ClientInfo> getClientInfoByID(@RequestParam("clientID") String clientID) {
        return clientService.getClientInfoByID(clientID);
    }
}
