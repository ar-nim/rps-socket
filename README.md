# rps-socket
Rock Paper Scissors Game using Java Sockets. Made as a group project for Network Programming Course in BINUS University.<br></br>

##Group Members:
- Arif Rahman Ibrahim - 1701302091
- Andrew - 1701305875
- Gigih Suryaman - 1701297822
- Joshua Kurniaputera - 1701298030

##Usage:
**Note: You MUST have Java Runtime Environment 8 installed!**
###Server:
```
java -jar server.jar <port>
OR
server <port>
Example:
java -jar server.jar 9999
OR
server.exe 9999
This will run the server at port 9999
```

###Client:
```
java -jar client.jar <host address> <port> OR client.exe <host address> <port>
Example:
java -jar client.jar localhost 9999 OR client.exe localhost 9999
java -jar client.jar 127.0.0.1 9999 OR client.exe 127.0.0.1 9999
Make sure that the server program is already running on the host you are connecting to<br></br>
```
