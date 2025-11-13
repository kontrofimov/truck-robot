package com.robot.controllers;

import com.robot.service.TruckRobotService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.robot.utils.CommandParser.getCommandList;
import static com.robot.utils.CommandParser.getPlaceCmdArgs;


@RestController
@RequestMapping("/api/robot")
@Validated
public class TruckRobotController {

    private final TruckRobotService truckRobotService;

    public TruckRobotController(TruckRobotService truckRobotService) {
        this.truckRobotService = truckRobotService;
    }

    @PostMapping("/command")
    public ResponseEntity<String> command(@RequestBody String commandReqStr) {
        List<String> cmdList = getCommandList(commandReqStr);
        String[] args = getPlaceCmdArgs(commandReqStr);
        String result = truckRobotService.processCommands(cmdList, args);
        return ResponseEntity.ok(result);
    }
}