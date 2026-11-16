@echo off
REM ==========================================================
REM Student Scholarship & Fee Concession System
REM Student: AYUSH SINGH (PIET25CR009) | Section: 3CSD
REM Poornima Institute of Engineering & Technology (PIET)
REM Compile and Run Script for Windows Command Prompt
REM ==========================================================

echo [1/3] Creating output directory 'bin'...
if not exist "bin" mkdir bin

echo [2/3] Compiling Java source files...
javac -encoding UTF-8 -cp "lib\mysql-connector-j-8.4.0.jar" -d bin src\db\*.java src\model\*.java src\dao\*.java src\ui\*.java src\Main.java src\TestRunner.java

if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Compilation failed! Please check JDK installation.
    pause
    exit /b %ERRORLEVEL%
)

echo [3/3] Launching Student Scholarship & Fee Concession System...
java -cp "bin;lib\mysql-connector-j-8.4.0.jar" Main

pause
