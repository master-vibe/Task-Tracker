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
task-cli <command> [arguments]
```

### For MACOS:

Open Terminal, navigate to the project root directory, and run:

```bash
./task-cli <command> [arguments]
```
##Available Commands:

Add a Task:
  Adds a new task with the given description.
  ```bash
  task-cli add "<description>"
  ```
Update a Task:
  Updates the task with the given ID and description.
  ```bash
  task-cli update <ID> "<description>"
  ```
Delete a Task:
  Deletes the task with the specified ID.
  ```bash
  task-cli delete <ID>
  ```
Mark a Task as In-Progress:
  Marks the task with the specified ID as in-progress.
  ```bash
  task-cli mark-in-progress <ID>
  ```
Mark a Task as Done:
  Marks the task with the specified ID as done.
  ```bash
  task-cli mark-done <ID>
  ```
List All Tasks:
  Lists all tasks.
  ```bash
  task-cli list
  ```
List Tasks with Filter:
  Lists tasks filtered by status (done, todo, in-progress).
  ```bash
  task-cli list <done/todo/in-progress>
  ```
Help:
  Displays this help message.
  ```bash
  task-cli help
  ```
