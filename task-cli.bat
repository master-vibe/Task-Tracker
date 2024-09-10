@echo off

set "sourceDir=src"
set "outputDir=classes"
set "mainClass=task_cli"  **Replace with your main class name (without extension)**

setlocal enabledelayedexpansion

:: Capture all additional arguments
set "additionalArgs=%*"

:: Create the output directory if it doesn't exist
if not exist "%outputDir%" mkdir "%outputDir%"

:: Compile if needed (check for missing directory or specific class files)
if not exist "%outputDir%" (
  echo Initializing...
) else (
  if not exist "%outputDir%\task_cli.class" (
    if not exist "%outputDir%\TaskManager.class" (
      if not exist "%outputDir%\Tasks.class" (
        echo Compiling...
        javac -d "%outputDir%" "!sourceDir!\task_cli.java" "!sourceDir!\TaskManager.java" "!sourceDir!\Tasks.java"
        if !errorlevel! neq 0 (
          echo Error: Compilation failed.
          exit /b 1
        )
      )
    )
  )
)

:: Run the Java main class with additional arguments
java -cp "%outputDir%" "%mainClass%" %additionalArgs%
if !errorlevel! neq 0 (
  echo Error: Application execution failed.
  exit /b 1
)

endlocal