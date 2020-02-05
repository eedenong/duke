import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String input) {
        super(input);
        this.keyword = input.split(" ")[1];
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.getTaskList();
        ArrayList<Task> foundList = new ArrayList<>();
        for (Task t: taskList) {
            String taskString = t.toString();
            // Split the task string by space
            String[] splitString = taskString.split(" ");
            // Iterate through the array and match the word
            for (String s: splitString) {
                if (s.equals(this.keyword)) {
                    // If the keyword is found, add to the foundList
                    foundList.add(t);
                    break;
                }
            }
        }
        ui.printLine();
        // Check if the foundList is empty
        if (foundList.size() > 0) {
            // If not 0, then print all the tasks that are found
            System.out.println("\t Here are the matching tasks in your list:");
            for (int i = 0; i < foundList.size(); i++) {
                System.out.println("\t " + (i + 1) + ". " + foundList.get(i).toString());
            }
        } else {
            // Found list is empty, tell the user that cannot find the matching task
            System.out.println("Sorry! No matching tasks found!");
        }
        ui.printLine();
    }
}