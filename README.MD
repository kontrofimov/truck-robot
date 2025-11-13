## Truck Robot API 


### 1. This API provides the following endpoints:

### 1.1 Place truck robot a table top:
POST /api/robot/place?x={x}&y={y}&direction={direction}

constraints: 
    0 <= x <= 4
    0 <= y <= 4
    direction is case sensitive, one of [NORTH, SOUTH, EAST, WEST]

Example request:
curl --location --request POST 'http://localhost:8080/api/robot/place?x=1&y=2&direction=NORTH'

Response:
Placed at 1,2,NORTH

### 1.2 Move truck in the direction of travel by 1 step:
   POST /api/robot/move

Example request:
curl --location --request POST 'http://localhost:8080/api/robot/move'

Response:
Moved to 1,4

### 1.3 Turn truck to the left:
   POST /api/robot/left

Example request:
curl --location --request POST 'http://localhost:8080/api/robot/left'

Response:
Facing: SOUTH

### 1.4 Turn truck to the right:
   POST /api/robot/right

Example request:
curl --location --request POST 'http://localhost:8080/api/robot/right

Response:
Facing: NORTH

### 1.5 View current position of the truck:
   GET /api/robot/report

Example request:
curl --location 'http://localhost:8080/api/robot/report'

Response:
1,4,NORTH

### 2. Limitations:
The API does not include a complete input validation or exception handling
