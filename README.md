##Elevator - Maven web application

###Deploy Application
1. $project_root: mvn clean package
2. Copy $project_root/target/elevator.war to Application Server of your choice. In my case it is JBoss 6.x

###Restful Service

####Start Elevator Service with default of 30 levels

Endpoint: /start
  
POST Consumes("text/plain")
  
Full URL: http://localhost:8080/elevator/rest/start


####Send request to elevator

Endpoint: /request

POST Consumes:application/json, Produces:application/json

Full URL: http://localhost:8080/elevator/rest/request

Request Body: {"from":5,"to":10}


####Get the top request

Endpoint: /request/top

GET Produces application/json

Full URL: http://localhost:8080/elevator/rest/request/top

  
####Get the list of requests

Endpoint: /request/all

GET Produces application/json 

Full URL: http://localhost:8080/elevator/rest/request/all


###Sample Interaction:
1. start service
2. send request 3,5
3. send request 5,10
4. send request 20,20
5. start service

###Corresponding output:
00:41:40,494 INFO  [org.jboss.resteasy.spi.ResteasyDeployment] Adding singleton resource com.intuit.elevator.service.ElevatorService from Application javax.ws.rs.core.Application
00:41:52,438 INFO  [com.intuit.elevator.controller.Controller] Starting service...
00:41:52,438 INFO  [com.intuit.elevator.controller.Controller] Started Thread-26
00:41:52,439 INFO  [com.intuit.elevator.controller.Elevator] Thread-26: No Requests. Wait 5 secs
00:41:57,440 INFO  [com.intuit.elevator.controller.Elevator] Thread-26: No Requests. Wait 5 secs
00:42:02,441 INFO  [com.intuit.elevator.controller.Elevator] Thread-26: No Requests. Wait 5 secs
00:42:07,441 INFO  [com.intuit.elevator.controller.Elevator] Thread-26: No Requests. Wait 5 secs
00:42:12,442 INFO  [com.intuit.elevator.controller.Elevator] Thread-26: No Requests. Wait 5 secs
00:42:17,443 INFO  [com.intuit.elevator.controller.Elevator] Thread-26: No Requests. Wait 5 secs
00:42:22,444 INFO  [com.intuit.elevator.controller.Elevator] Thread-26: No Requests. Wait 5 secs
00:42:27,445 INFO  [com.intuit.elevator.controller.Elevator] Thread-26: No Requests. Wait 5 secs
00:42:32,445 INFO  [com.intuit.elevator.controller.Elevator] Thread-26: No Requests. Wait 5 secs
00:42:37,446 INFO  [com.intuit.elevator.controller.Elevator] Thread-26: No Requests. Wait 5 secs
00:42:42,447 INFO  [com.intuit.elevator.controller.Elevator] PROCESS REQUEST From:3 To:5
00:42:43,449 INFO  [com.intuit.elevator.controller.Elevator] 07/30/2015 12:42:43 AM Level:0 Status:UP
00:42:44,451 INFO  [com.intuit.elevator.controller.Elevator] 07/30/2015 12:42:44 AM Level:1 Status:UP
00:42:45,451 INFO  [com.intuit.elevator.controller.Elevator] 07/30/2015 12:42:45 AM Level:2 Status:UP
00:42:46,453 INFO  [com.intuit.elevator.controller.Elevator] 07/30/2015 12:42:46 AM Level:3 Status:OPEN
00:42:47,454 INFO  [com.intuit.elevator.controller.Elevator] 07/30/2015 12:42:47 AM Level:3 Status:CLOSE
00:42:48,455 INFO  [com.intuit.elevator.controller.Elevator] 07/30/2015 12:42:48 AM Level:3 Status:UP
00:42:49,456 INFO  [com.intuit.elevator.controller.Elevator] 07/30/2015 12:42:49 AM Level:4 Status:UP
00:42:50,457 INFO  [com.intuit.elevator.controller.Elevator] 07/30/2015 12:42:50 AM Level:5 Status:OPEN
00:42:51,458 INFO  [com.intuit.elevator.controller.Elevator] 07/30/2015 12:42:51 AM Level:5 Status:CLOSE
00:42:52,459 INFO  [com.intuit.elevator.controller.Elevator] COMPLETED From:3 To:5
00:42:52,460 INFO  [com.intuit.elevator.controller.Elevator] PROCESS REQUEST From:5 To:10
00:42:53,460 INFO  [com.intuit.elevator.controller.Elevator] 07/30/2015 12:42:53 AM Level:5 Status:OPEN
00:42:54,461 INFO  [com.intuit.elevator.controller.Elevator] 07/30/2015 12:42:54 AM Level:5 Status:CLOSE
00:42:55,462 INFO  [com.intuit.elevator.controller.Elevator] 07/30/2015 12:42:55 AM Level:5 Status:UP
00:42:56,464 INFO  [com.intuit.elevator.controller.Elevator] 07/30/2015 12:42:56 AM Level:6 Status:UP
00:42:57,465 INFO  [com.intuit.elevator.controller.Elevator] 07/30/2015 12:42:57 AM Level:7 Status:UP
00:42:58,465 INFO  [com.intuit.elevator.controller.Elevator] 07/30/2015 12:42:58 AM Level:8 Status:UP
00:42:59,467 INFO  [com.intuit.elevator.controller.Elevator] 07/30/2015 12:42:59 AM Level:9 Status:UP
00:43:00,467 INFO  [com.intuit.elevator.controller.Elevator] 07/30/2015 12:43:00 AM Level:10 Status:OPEN
00:43:01,469 INFO  [com.intuit.elevator.controller.Elevator] 07/30/2015 12:43:01 AM Level:10 Status:CLOSE
00:43:02,469 INFO  [com.intuit.elevator.controller.Elevator] COMPLETED From:5 To:10
00:43:02,470 WARN  [com.intuit.elevator.controller.Elevator] Not a valid request. SKIP From:20 To:20
00:43:02,470 INFO  [com.intuit.elevator.controller.Elevator] Thread-26: No Requests. Wait 5 secs
00:43:07,470 INFO  [com.intuit.elevator.controller.Elevator] Thread-26: No Requests. Wait 5 secs
00:43:12,471 INFO  [com.intuit.elevator.controller.Elevator] Thread-26: No Requests. Wait 5 secs
00:43:16,890 WARN  [com.intuit.elevator.controller.Controller] Service already started

###Unit Tests

There are unit testcases that cover all functionalities of Elevator and Controller. Use the code snippet below for running the tests.

$project_root: mvn test 

Output

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.intuit.elevator.tests.AllTests
log4j:WARN No appenders could be found for logger (com.intuit.elevator.controller.Controller).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
Tests run: 9, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 5.104 sec

Results :

Tests run: 9, Failures: 0, Errors: 0, Skipped: 0
