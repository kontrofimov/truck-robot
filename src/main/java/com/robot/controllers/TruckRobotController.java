package com.robot.controllers;

import com.robot.model.Direction;
import com.robot.service.TruckRobotService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/robot")
@Validated
public class TruckRobotController {

    private final TruckRobotService truckRobotService;

    public TruckRobotController(TruckRobotService truckRobotService) {
        this.truckRobotService = truckRobotService;
    }

    @PostMapping("/place")
    public ResponseEntity<String> place(
            @RequestParam @NotNull Integer x,
            @RequestParam @NotNull Integer y,
            @RequestParam @NotNull Direction direction) {

        String result = truckRobotService.place(x, y, direction);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/move")
    public ResponseEntity<String> move() {
        String result = truckRobotService.move();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/left")
    public ResponseEntity<String> turnLeft() {
        String result = truckRobotService.turnLeft();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/right")
    public ResponseEntity<String> turnRight() {
        String result = truckRobotService.turnRight();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/report")
    public ResponseEntity<String> report() {
        String result = truckRobotService.report();
        return ResponseEntity.ok(result);
    }
}