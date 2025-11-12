package com.robot.service;

import com.robot.model.Direction;
import com.robot.model.Position;
import com.robot.model.Robot;
import com.robot.model.Table;
import org.springframework.stereotype.Service;


@Service
public class TruckRobotService {
    private final Robot robot;
    private final Table table;

    public TruckRobotService() {
        this.robot = new Robot();
        this.table = new Table(5, 5);
    }

    public String place(int x, int y, Direction direction) {
        Position position = new Position(x, y);

        if (table.isValidPosition(position)) {
            return "Invalid position.";
        }

        robot.place(position, direction);
        return "Placed at " + x + "," + y + "," + direction;
    }

    public String move() {
        if (!robot.isPlaced()) {
            return "Not placed.";
        }

        Position newPosition = robot.getPosition().move(robot.getDirection());

        if (table.isValidPosition(newPosition)) {
            return "Move ignored.";
        }

        robot.move();
        return "Moved to " + robot.getPosition().getX() + "," + robot.getPosition().getY();
    }

    public String turnLeft() {
        if (!robot.isPlaced()) {
            return "Not placed.";
        }

        robot.turnLeft();
        return "Facing: " + robot.getDirection();
    }

    public String turnRight() {
        if (!robot.isPlaced()) {
            return "Not placed.";
        }

        robot.turnRight();
        return "Facing: " + robot.getDirection();
    }

    public String report() {
        return robot.report();
    }

}

