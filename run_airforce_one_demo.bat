@echo off
echo Compiling and running AirforceOne Singleton Pattern Demo...
javac -d target\classes src\main\java\com\boot\designpatterns\singleton\AirforceOne.java src\main\java\com\boot\designpatterns\singleton\Client.java src\main\java\com\boot\designpatterns\singleton\AirforceOneDemo.java
java -cp target\classes com.boot.designpatterns.singleton.AirforceOneDemo
pause