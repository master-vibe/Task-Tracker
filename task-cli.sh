#!/bin/bash

# Enable exit on error
set -e

# Get the directory where the script is located
scriptDir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Define the sourceDir and outputDir
sourceDir="$scriptDir/src"
outputDir="$scriptDir/bin"
specificFile="$sourceDir"

# Define the main class to run (without .java or .class extension)
mainClass="task_cli"

# Capture all additional arguments passed to the script
additionalArgs="$@"

# Create the output directory if it does not exist
mkdir -p "$outputDir"

# Define the Java files to be compiled
javaFiles=("$specificFile/task_cli.java" "$specificFile/Tasks.java" "$specificFile/TaskManager.java")

# Check if any of the Java files are newer than the class files
compileNeeded=1

for javaFile in "${javaFiles[@]}"; do
    classFile="$outputDir/$(basename "$javaFile" .java).class"
    if [[ ! -f "$classFile" || "$javaFile" -nt "$classFile" ]]; then
        compileNeeded=0
        break
    fi
done

# Compile if needed
if [[ "$compileNeeded" -eq 0 ]]; then
    echo "Compiling Java files..."
    javac -d "$outputDir" "${javaFiles[@]}"
    if [[ $? -ne 0 ]]; then
        echo "Compilation failed!"
        exit 1
    fi
fi

# Run the Java main class with additional arguments
echo "Running $mainClass with arguments $additionalArgs..."
java -cp "$outputDir" "$mainClass" $additionalArgs

if [[ $? -ne 0 ]]; then
    echo "Program execution failed!"
    exit 1
fi

echo "Execution succeeded!"
