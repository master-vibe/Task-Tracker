#!/bin/bash

sourceDir="src"
outputDir="classes"

mainClass="task_cli"  # Replace with your main class name (without extension)

# Capture all additional arguments
additionalArgs="$*"

# Create the output directory if it doesn't exist
if [ ! -d "$outputDir" ]; then
    mkdir "$outputDir"
fi

# Compile if needed (check for missing directory or specific class files)
if [ ! -d "$outputDir" ] || [ ! -f "classes/task_cli.class" ] || [ ! -f "classes/TaskManager.class" ] || [ ! -f "classes/Tasks.class" ]; then
    echo "Initializing..."
    javac -d classes src/task_cli.java src/TaskManager.java src/Tasks.java
    if [ $? -ne 0 ]; then
        echo "Error: Compilation failed."
        exit 1
    fi
fi

# Run the Java main class with additional arguments
java -cp "$outputDir" "$mainClass" $additionalArgs
if [ $? -ne 0 ]; then
    echo "Error: Application execution failed."
    exit 1
fi