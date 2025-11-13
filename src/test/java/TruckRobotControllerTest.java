import com.robot.controllers.TruckRobotController;
import com.robot.service.TruckRobotService;
import com.robot.utils.CommandParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TruckRobotControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TruckRobotController truckRobotController;
    @Mock
    private TruckRobotService truckRobotService;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void testProcessCommands_success() throws Exception {
        String commandString = "PLACE 0,1,NORTH MOVE RIGHT MOVE LEFT MOVE LEFT REPORT";
        String expected = "0,3,WEST";

        List<String> commands = CommandParser.getCommandList(commandString);
        String[] args = CommandParser.getPlaceCmdArgs(commandString);

        when(truckRobotService.processCommands(commands, args)).thenReturn(expected);
        ResponseEntity<String> response = truckRobotController.command(commandString);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testProcessCommands_move_south_ignored() throws Exception {
        String commandString = "PLACE 0,0,SOUTH MOVE REPORT";
        String expected = "0,0,SOUTH";

        List<String> commands = CommandParser.getCommandList(commandString);
        String[] args = CommandParser.getPlaceCmdArgs(commandString);

        when(truckRobotService.processCommands(commands, args)).thenReturn(expected);
        ResponseEntity<String> response = truckRobotController.command(commandString);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testProcessCommands_move_west_ignored() throws Exception {
        String commandString = "PLACE 0,4,WEST MOVE MOVE REPORT";
        String expected = "0,4,WEST";

        List<String> commands = CommandParser.getCommandList(commandString);
        String[] args = CommandParser.getPlaceCmdArgs(commandString);

        when(truckRobotService.processCommands(commands, args)).thenReturn(expected);
        ResponseEntity<String> response = truckRobotController.command(commandString);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testProcessCommands_move_north_ignored() throws Exception {
        String commandString = "PLACE 4,4,NORTH MOVE REPORT";
        String expected = "4,4,NORTH";

        List<String> commands = CommandParser.getCommandList(commandString);
        String[] args = CommandParser.getPlaceCmdArgs(commandString);

        when(truckRobotService.processCommands(commands, args)).thenReturn(expected);
        ResponseEntity<String> response = truckRobotController.command(commandString);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testProcessCommands_move_east_ignored() throws Exception {
        String commandString = "PLACE 4,2,EAST MOVE REPORT";
        String expected = "4,2,EAST";

        List<String> commands = CommandParser.getCommandList(commandString);
        String[] args = CommandParser.getPlaceCmdArgs(commandString);

        when(truckRobotService.processCommands(commands, args)).thenReturn(expected);
        ResponseEntity<String> response = truckRobotController.command(commandString);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }


    @Test
    public void testProcessCommands_place_invalid_location1() throws Exception {
        String commandString = "PLACE 5,1,EAST REPORT";
        String expected = "Not placed.";

        List<String> commands = CommandParser.getCommandList(commandString);
        String[] args = CommandParser.getPlaceCmdArgs(commandString);

        when(truckRobotService.processCommands(commands, args)).thenReturn(expected);
        ResponseEntity<String> response = truckRobotController.command(commandString);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testProcessCommands_place_invalid_location2() throws Exception {
        String commandString = "PLACE -1,0,WEST REPORT";
        String expected = "Not placed.";

        List<String> commands = CommandParser.getCommandList(commandString);
        String[] args = CommandParser.getPlaceCmdArgs(commandString);

        when(truckRobotService.processCommands(commands, args)).thenReturn(expected);
        ResponseEntity<String> response = truckRobotController.command(commandString);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testProcessCommands_place_invalid_location3() throws Exception {
        String commandString = "PLACE 1,5,WEST REPORT";
        String expected = "Not placed.";

        List<String> commands = CommandParser.getCommandList(commandString);
        String[] args = CommandParser.getPlaceCmdArgs(commandString);

        when(truckRobotService.processCommands(commands, args)).thenReturn(expected);
        ResponseEntity<String> response = truckRobotController.command(commandString);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testProcessCommands_place_invalid_location4() throws Exception {
        String commandString = "PLACE 1,-1,WEST REPORT";
        String expected = "Not placed.";

        List<String> commands = CommandParser.getCommandList(commandString);
        String[] args = CommandParser.getPlaceCmdArgs(commandString);

        when(truckRobotService.processCommands(commands, args)).thenReturn(expected);
        ResponseEntity<String> response = truckRobotController.command(commandString);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testProcessCommands_rotate_left() throws Exception {
        String commandString = "PLACE 3,2,WEST LEFT LEFT LEFT LEFT";
        String expected = "3,2,WEST";

        List<String> commands = CommandParser.getCommandList(commandString);
        String[] args = CommandParser.getPlaceCmdArgs(commandString);

        when(truckRobotService.processCommands(commands, args)).thenReturn(expected);
        ResponseEntity<String> response = truckRobotController.command(commandString);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testProcessCommands_rotate_right() throws Exception {
        String commandString = "PLACE 3,2,WEST RIGHT RIGHT RIGHT RIGHT";
        String expected = "3,2,WEST";

        List<String> commands = CommandParser.getCommandList(commandString);
        String[] args = CommandParser.getPlaceCmdArgs(commandString);

        when(truckRobotService.processCommands(commands, args)).thenReturn(expected);
        ResponseEntity<String> response = truckRobotController.command(commandString);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testProcessCommands_not_placed_ignored_command() throws Exception {
        String commandString = "PLACE 1,-1,NORTH MOVE";
        String expected = "Not placed.";

        List<String> commands = CommandParser.getCommandList(commandString);
        String[] args = CommandParser.getPlaceCmdArgs(commandString);

        when(truckRobotService.processCommands(commands, args)).thenReturn(expected);
        ResponseEntity<String> response = truckRobotController.command(commandString);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testProcessCommands_not_placed_ignored_command2() throws Exception {
        String commandString = "MOVE REPORT";
        String expected = "Not placed.";

        List<String> commands = CommandParser.getCommandList(commandString);
        String[] args = CommandParser.getPlaceCmdArgs(commandString);

        when(truckRobotService.processCommands(commands, args)).thenReturn(expected);
        ResponseEntity<String> response = truckRobotController.command(commandString);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testProcessCommands_command_args_lower_case() throws Exception {
        String commandString = "place 0,1,north REPORT";
        String expected = "Not placed.";

        List<String> commands = CommandParser.getCommandList(commandString);
        String[] args = CommandParser.getPlaceCmdArgs(commandString);

        when(truckRobotService.processCommands(commands, args)).thenReturn(expected);
        ResponseEntity<String> response = truckRobotController.command(commandString);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testProcessCommands_command_args_extra_space() throws Exception {
        String commandString = "PLACE  0,1,EAST REPORT"; //args for PLACE command aren't recognised due to the extra space as expected
        String expected = "Not placed.";

        List<String> commands = CommandParser.getCommandList(commandString);
        String[] args = CommandParser.getPlaceCmdArgs(commandString);

        when(truckRobotService.processCommands(commands, args)).thenReturn(expected);
        ResponseEntity<String> response = truckRobotController.command(commandString);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testProcessCommands_command_args_incorrect_order() throws Exception {
        String commandString = "0,1,EAST PLACE REPORT";
        String expected = "Not placed.";

        List<String> commands = CommandParser.getCommandList(commandString);
        String[] args = CommandParser.getPlaceCmdArgs(commandString);

        when(truckRobotService.processCommands(commands, args)).thenReturn(expected);
        ResponseEntity<String> response = truckRobotController.command(commandString);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testProcessCommands_command_args_incorrect_args() throws Exception {
        String commandString = "PLACE  3,3, WEST REPORT";
        String expected = "Not placed.";

        List<String> commands = CommandParser.getCommandList(commandString);
        String[] args = CommandParser.getPlaceCmdArgs(commandString);

        when(truckRobotService.processCommands(commands, args)).thenReturn(expected);
        ResponseEntity<String> response = truckRobotController.command(commandString);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testProcessCommands_command_args_incorrect_args2() throws Exception {
        String commandString = "PLACE 3, 3 ,WEST REPORT";
        String expected = "Not placed.";

        List<String> commands = CommandParser.getCommandList(commandString);
        String[] args = CommandParser.getPlaceCmdArgs(commandString);

        when(truckRobotService.processCommands(commands, args)).thenReturn(expected);
        ResponseEntity<String> response = truckRobotController.command(commandString);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testProcessCommands_invalid_command() throws Exception {
        String commandString = "PLACE 3,3,WEST TURNLEFT ,M0VE REPORT";
        String expected = "3,3,WEST";

        List<String> commands = CommandParser.getCommandList(commandString);
        String[] args = CommandParser.getPlaceCmdArgs(commandString);

        when(truckRobotService.processCommands(commands, args)).thenReturn(expected);
        ResponseEntity<String> response = truckRobotController.command(commandString);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testProcessCommands_invalid_place_direction() throws Exception {
        String commandString = "PLACE 0,0,W REPORT";
        String expected = "Not placed.";

        List<String> commands = CommandParser.getCommandList(commandString);
        String[] args = CommandParser.getPlaceCmdArgs(commandString);

        when(truckRobotService.processCommands(commands, args)).thenReturn(expected);
        ResponseEntity<String> response = truckRobotController.command(commandString);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

}
