@echo off
setlocal enabledelayedexpansion

:: Get the directory where the script is located
set "scriptDir=%~dp0"

:: Define the sourceDir and outputDir
set "sourceDir=%scriptDir%src"
set "outputDir=%scriptDir%bin"
set "specificFile=%sourceDir%"

:: Define the main class to run
set "mainClass=task_cli"  :: Replace with the name of your main class without the .java or .class extension

:: Remove trailing backslash if exists
if "%sourceDir:~-1%"=="\" set "sourceDir=%sourceDir:~0,-1%"
if "%outputDir:~-1%"=="\" set "outputDir=%outputDir:~0,-1%"

:: Capture all additional arguments passed to the batch file
set "additionalArgs=%*"

:: Create the output directory if it does not exist
if not exist "%outputDir%" mkdir "%outputDir%"

:: Define the Java files to be compiled
set "javaFiles=%specificFile%\task_cli.java %specificFile%\Tasks.java %specificFile%\TaskManager.java"

:: Check if any of the Java files are newer than the class files
set "compileNeeded=1"

:: Compile if needed
if "!compileNeeded!"=="1" (
    javac -d "%outputDir%" "%specificFile%\task_cli.java" "%specificFile%\Tasks.java" "%specificFile%\TaskManager.java"
    if %errorlevel% neq 0 (
        exit /b 1
    ) else (
        set "compileNeeded=0"
    )
)
:: Run the Java main class with additional arguments
java -cp "%outputDir%" %mainClass% %additionalArgs%
if %errorlevel% neq 0 (
    exit /b 1
)
endlocal
exit /b
