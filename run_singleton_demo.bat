@echo off
echo Compiling and running Singleton Pattern Demo...
javac -d target\classes src\main\java\com\boot\designpatterns\singleton\*.java
java -cp target\classes com.boot.designpatterns.singleton.SingletonDemo
pause