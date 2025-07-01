@echo off
echo Compiling and running Aircraft Builder Pattern Demo...
javac -d target\classes src\main\java\com\boot\designpatterns\builder\aircraft\*.java
java -cp target\classes com.boot.designpatterns.builder.aircraft.AircraftClient
pause