# Task Tracker CLI

Task Tracker CLI is a command-line interface application built in Java that allows users to manage tasks with various operations like adding, updating, deleting, marking as in-progress or done, and listing tasks. This tool is designed to be simple and efficient for task management via terminal commands.

## Features

- **Add Task:** Add a new task with a description.
- **Update Task:** Update an existing task by its ID.
- **Delete Task:** Remove a task using its ID.
- **Mark Task as In-Progress:** Change the status of a task to in-progress using its ID.
- **Mark Task as Done:** Change the status of a task to done using its ID.
- **List:** View all tasks or filter by status (done, todo, in-progress).
- **Help Command:** Display available commands and their usage.

## Prerequisites

- **Java Development Kit (JDK):** Make sure you have JDK installed on your system. You can download it from the [official Oracle website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
- **Command-Line Interface (CLI):** Use a terminal (macOS/Linux) or Command Prompt (Windows).

## Setup

1. Clone the repository or download the source code to your local machine.
2. Ensure that you have the batch script (`task-cli.bat` for Windows) or shell script (`task-cli.sh` for macOS/Linux) included in the project directory.

## Usage

The provided batch or shell script will handle both the compilation and execution of the Java program. To use the Task CLI, follow these steps based on your operating system:

### Important Note

You need to navigate to the root folder of the project where the batch (`task-cli.bat`) or shell script (`task-cli.sh`) is located. Alternatively, you can add the script to your system's PATH environment variable to run it from any location.

### For Windows:

Open Command Prompt, navigate to the project root directory, and run:

```bash
task-cli.bat <command> [arguments]

### For MACOS:

Open Terminal, navigate to the project root directory, and run:

```bash
./task-cli.sh <command> [arguments]
