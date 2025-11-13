package com.robot.utils;
import com.robot.model.CommandEnum;
import com.robot.model.Direction;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class CommandParser {

    private static final Set<String> VALID_COMMANDS =
            Arrays.stream(CommandEnum.values())
                    .map(Enum::name)
                    .collect(Collectors.toSet());

    private static final Set<String> VALID_DIRECTIONS =
            Arrays.stream(Direction.values())
                    .map(Enum::name)
                    .collect(Collectors.toSet());

    public static List<String> getCommandList(String commandString) {
        String cmdStr = commandString.trim();
        Scanner sc = new Scanner(cmdStr);
        List<String> list = new ArrayList<String>();
        //iterate through the command string split them into the list
        while (sc.hasNext()) {

            String next = sc.next();
            //check if the command matches the known commands in commandEnum
            if (VALID_COMMANDS.contains(next)) {
                list.add(next);
            }
        }
        return list;
    }

    public static String[] getPlaceCmdArgs(String commandString) {
        String[] args = null;
        String placeCmd = CommandEnum.PLACE.toString();

        try {
            if (commandString.contains(placeCmd)) {
                //find the exact substring with args after PLACE cmd
                int placeCmdStartIndex = commandString.indexOf(placeCmd);
                int placeCmdEndIndex = placeCmdStartIndex + placeCmd.length();

                String commandStrSub = commandString.substring(placeCmdEndIndex);

                Scanner sc = new Scanner(commandStrSub);
                sc.useDelimiter(" ");

                if (sc.hasNext()) {
                    // check if the arguments for PLACE command are valid
                    String argsRegexPattern = "^(\\d+),(\\d+),([A-Z]+)$";
                    Pattern p = Pattern.compile(argsRegexPattern);
                    Matcher m = p.matcher(sc.next());
                    String placeDirection = m.group(3).toUpperCase();
                    if (m.matches() && VALID_DIRECTIONS.contains(placeDirection)) {
                        args = new String[]{ m.group(1), m.group(2), placeDirection };
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error in getPlaceCmdArgs: " + e.getMessage());
        }
        return args;
    }

}