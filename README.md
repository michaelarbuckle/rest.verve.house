# rest.verve.house
rest web services with mongoDB 

Internet of things (IOT) schema 
Description: designed as a canonical schema that can be integrated with other IOT schema such as Apple's Home Kit, Nest's , TI's exosite

User - User of system and/or inhabitant of structure
Space	- An outdoor space, a structure or an interior space or a room 
Space-role - A user’s role or relationship to a space
Device - A microcontroller such as the CC3200 board with a unique network address (eg WIFI) that controls sensors and converts sensor output to digital data
Sensor - Each unique measure of an environmental variable
Timeseries - A sequence of sensor values at a given interval over a period such as an hour, day or week
Actuator - A machine for controlling or influencing environment
Action - An action available from an actuator
ActionParameter - One or more parameter values defining an action
Rule -	A condition of input sensor data that triggers a series of notifications and/or actions
Agent - A smart agent that can interact via chat
Message - A notification, a command or a conversational message 

