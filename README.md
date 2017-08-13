Weight Tracker
==============
For the purpose of illustration, consider that this application is running on localhost:8080.

## Technologies Used
Spring MVC, EasyRules, Morphia with MongoDB

###Part 1 : Infromation from Sensors
Different sensors communicate with this application using RESTful web services. 
Each sensor sends weight information every 5 seconds by sending data to  http://localhost:8080/weight using HTTP post request. The data can be as following:

~~~
{"timeStamp": "1502586399207", "value": "150"}
~~~

To view all the data between specific time-range use following HTTP Get request.
~~~
http://localhost:8080/weight?from=1502652953725&to=1502652953755
http://localhost:8080/weight?from=1502652953725
http://localhost:8080/weight?to=1502652953725
~~~

A sample output for HTTP Get request is shown below:
~~~
[
    {
        "id": { "timestamp": 1502652954, "machineIdentifier": 5833587, "processIdentifier": 12896, "counter": 6738920, "time": 1502652954000, "date": 1502652954000,"timeSecond": 1502652954 },
        "sesonrIP": "127.0.0.1",
        "timeStamp": 1502652953725,
        "value": 150
    }
]
~~~

###Part 2 :  Notification based on Rules
We want to notify user when the weight reported by a sensor falls below 10% or goes above 10%. To perform this, I have used EasyRules(http://www.easyrules.org).

This app will automatically identify the IP address of Sensor. If a sensor sends request for a first time then it will be considered as a base value.

Notify endpoint can be used to see all notifications or notifications within a range. You can use following HTTP GET requests:
~~~
http://localhost:8080/notify
http://localhost:8080/notify?from=1502651512023&to=1502651517035
~~~


## How to use it
Clone it and use it. It has main method which will start server on it's own.

HTTP Get request can be modified to fetch sensor specific data.