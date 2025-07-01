@echo off
echo Compiling and running Builder Pattern Demo...
javac -d target\classes src\main\java\com\boot\designpatterns\builder\*.java
java -cp target\classes com.boot.designpatterns.builder.BuilderDemo
pause