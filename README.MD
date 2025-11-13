# Truck Robot API 

### 1. Prerequisites
#### Install Java 21 and set JAVA_HOME on Windows     
#### Run command to check Java version:
    java -version

#### Run the command to start the Spring Boot app:
    .\gradlew bootRun


### 2. REST API endpoint
    POST /api/robot/command
    
#### Supported Commands should be included in the request body as 'Content-Type: text/plain':
    PLACE X,Y,F
    MOVE
    LEFT
    RIGHT
    REPORT

#### Constraints:
    0 <= X <= 4
    0 <= Y <= 4
    F for NORTH, SOUTH, EAST or WEST.
     
#### Example Requests:

    curl --location 'http://localhost:8080/api/robot/command' \
    --header 'Content-Type: text/plain' \
    --data 'PLACE 0,0,NORTH MOVE RIGHT MOVE REPORT'

#### Example Respone:
    1,1,EAST

### 3. Specifications:
The application meets the following requirements:

    The application is created with Spring Boot 3.5.7 and Java 21.
    Input is realised over the REST        
    The square tabletop of dimensions 5 x 5 units.
    The robot that is not on the table can choose the ignore the MOVE, LEFT, RIGHT and REPORT commands.
    The robot must not fall off the table during movement. This also includes the initial placement of the truck robot.
    Any move that would cause the robot to fall must be ignored.
    It is not required to provide any graphical output showing the movement of the truck robot.

### 5. Testing
#### Functionality is covered with unit testing, test cases can be found in `TruckRobotControllerTest`
