package com.robot.model;

public class Robot {
    private Position position;
    private Direction direction;
    private boolean placed;

    public Robot() {
        this.placed = false;
    }

    public void place(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
        this.placed = true;
    }

    public boolean isPlaced() {
        return placed;
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void move() {
        if (placed) {
            position = position.move(direction);
        }
    }

    public void turnLeft() {
        if (placed) {
            direction = direction.turnLeft();
        }
    }

    public void turnRight() {
        if (placed) {
            direction = direction.turnRight();
        }
    }

    public String report() {
        if (!placed) {
            return "Not placed.";
        }
        return position.getX() + "," + position.getY() + "," + direction;
    }
}

