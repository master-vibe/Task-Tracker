public class task_cli {
    public static void main(String args[]) {
        TaskManager taskManager = new TaskManager();
        try {
            if (args.length == 0) {
                System.out.println("Error: No command provided.");
                help();
                return;
            }

            String cmd_function = args[0];

            switch (cmd_function) {
                case "add":
                    if (args.length < 2) {
                        System.out.println("Error: Missing description.");
                        System.out.println("\tUsage: task_cli add \"<description>\"");
                        return;
                    }
                    String cmd_description = args[1];
                    taskManager.add(cmd_description);
                    break;

                case "update":
                    if (args.length < 3) {
                        System.out.println("Error: Missing ID or description.");
                        System.out.println("\tUsage: task_cli update <ID> \"<description>\"");
                        return;
                    }
                    taskManager.update(args[1], args[2]);
                    break;

                case "delete":
                    if (args.length < 2) {
                        System.out.println("Error: Missing ID.");
                        System.out.println("\tUsage: task_cli delete <ID>");
                        return;
                    }
                    taskManager.delete(args[1]);
                    break;

                case "mark-in-progress":
                    if (args.length < 2) {
                        System.out.println("Error: Missing ID.");
                        System.out.println("\tUsage: task_cli mark-in-progress <ID>");
                        return;
                    }
                    taskManager.mark(args[1], "in-progress");
                    break;

                case "mark-done":
                    if (args.length < 2) {
                        System.out.println("Error: Missing ID.");
                        System.out.println("\tUsage: task_cli mark-done <ID>");
                        return;
                    }
                    taskManager.mark(args[1], "done");
                    break;

                case "list":
                    if (args.length == 1) {
                        taskManager.list();
                    } else if (args.length == 2 && args[1].matches("(?i)^(done|todo|in-progress)$")) {
                        taskManager.list_filter(args[1]);
                    } else {
                        System.out.println("Error: Invalid list filter.");
                        System.out.println("\tUsage: task_cli list <done/todo/in-progress>");
                    }
                    break;

                case "help":
                    help();
                    break;

                default:
                    System.out.println("Unknown command: " + cmd_function);
                    help();
                    break;
            }

            taskManager.saveTasks();

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Command not provided correctly.");
            help();
        }
    }

    private static void help() {
        System.out.println("Available Commands:");
        System.out.println("\tadd \"<description>\"             : Adds a new task with the given description.");
        System.out.println("\tupdate <ID> \"<description>\"      : Updates the task with the given ID and description.");
        System.out.println("\tdelete <ID>                      : Deletes the task with the specified ID.");
        System.out.println("\tmark-in-progress <ID>            : Marks the task with the specified ID as in-progress.");
        System.out.println("\tmark-done <ID>                   : Marks the task with the specified ID as done.");
        System.out.println("\tlist                             : Lists all tasks.");
        System.out.println("\tlist <done/todo/in-progress>     : Lists tasks filtered by status.");
        System.out.println("\thelp                             : Displays this help message.");
    }
}
