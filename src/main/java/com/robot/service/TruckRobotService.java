package com.robot.service;

import com.robot.model.*;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TruckRobotService {
    private final Robot robot;
    private final Table table;

    public TruckRobotService() {
        this.robot = new Robot();
        this.table = new Table(5, 5);
    }

    public String processCommands (List<String> commands, String[] args) {

        String result = "";
        for (String command : commands) {
            switch (CommandEnum.valueOf(command)) {
                case PLACE:
                    if (args != null && args.length == 3) {
                        place(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Direction.valueOf(args[2]));
                    }
                    break;
                case MOVE:
                    move();
                    break;
                case LEFT:
                    turnLeft();
                    break;
                case RIGHT:
                    turnRight();
                    break;
                case REPORT:
                    result = report();
                    break;
            }
        };
        return result;
    }

    private void place(int x, int y, Direction direction) {
        Position position = new Position(x, y);
        if (table.isValidPosition(position)) {
            robot.place(position, direction);
        }
    }

    private void move() {
        if (robot.isPlaced()) {
            Position newPosition = robot.getPosition().move(robot.getDirection());
            if (table.isValidPosition(newPosition)) {
                robot.move();
            }
        }
    }

    private void turnLeft() {
        if (robot.isPlaced()) {
            robot.turnLeft();
        }
    }

    private void turnRight() {
        if (robot.isPlaced()) {
            robot.turnRight();
        }
    }

    public String report() {
        return robot.report();
    }

}

