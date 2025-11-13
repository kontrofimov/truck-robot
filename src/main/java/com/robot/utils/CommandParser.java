package com.robot.utils;
import com.robot.model.CommandEnum;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class CommandParser {

    private static final Set<String> VALID_COMMANDS =
            Arrays.stream(CommandEnum.values())
                    .map(Enum::name)
                    .collect(Collectors.toSet());

    public static List<String> getCommandList(String commandString) {
        String cmdStr = commandString.trim().toUpperCase();
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

        if (commandString.contains(CommandEnum.PLACE.toString())) {
            Scanner sc = new Scanner(commandString);

            while (sc.hasNext()) {
                String next = sc.next();
                // check if the arguments for PLACE command are valid
                String argsRegexPattern = "^(\\d+),(\\d+),([A-Z]+)$";
                Pattern p = Pattern.compile(argsRegexPattern);
                Matcher m = p.matcher(next);
                if (m.matches()) {
                    args = new String[]{m.group(1), m.group(2), m.group(3)};
                }
            }
        } else {
            System.out.printf("commandString %s doesn't contain PLACE args", commandString);
        }

        return args;
    }


}