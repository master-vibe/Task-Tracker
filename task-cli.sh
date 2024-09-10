#!/bin/bash

# Get the script directory (works across POSIX shells)
scriptDir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

# Define source and output directories
sourceDir="$scriptDir/src"
outputDir="$scriptDir/bin"
specificFile="$sourceDir"

# Define the main class to run
mainClass="task_cli"  # Replace with your main class name (without extension)

# Remove trailing backslashes (if any)
sourceDir="${sourceDir%*/}"  # More portable way for cross-platform compatibility
outputDir="${outputDir%*/}"

# Capture all additional arguments
additionalArgs="$*"

# Create the output directory if it doesn't exist
if [ ! -d "$outputDir" ]; then
  mkdir "$outputDir"
fi

# Define Java files for compilation
javaFiles="$specificFile/task_cli.java $specificFile/Tasks.java $specificFile/TaskManager.java"

# Check for newer Java files (POSIX-compliant approach)
compileNeeded=1
for javaFile in $javaFiles; do
  if [[ $javaFile -nt "$outputDir/${javaFile%.java}.class" ]]; then
    compileNeeded=0
    break
  fi
done

# Compile if needed
if [ $compileNeeded -eq 0 ]; then
  javac -d "$outputDir" "$javaFiles"
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