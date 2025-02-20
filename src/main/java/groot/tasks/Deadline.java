package groot.tasks;

import groot.ui.Ui;

public class Deadline extends Task {
    private String deadlineDue;
    public Deadline(String description, String deadlineDue, String key) {
        super(description);
        this.deadlineDue = deadlineDue;
        taskType = "D";
        this.key = key;
    }

    public static Deadline createDeadline(String description) {
        try {
            String[] descriptionParts = description.split(" /by ", 2);
            String deadline = descriptionParts[0].trim();
            String deadlineDue = descriptionParts[1].trim();

            return new Deadline(deadline, deadlineDue, description);
        } catch (IndexOutOfBoundsException e) {
            Ui.say("Wrong format for Deadline!" + Ui.NEW_LINE +
                Ui.INDENT + "Use the format: {Task /by due date}");
            return null;
        }
    }

    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + " (by: " + deadlineDue + ")";
    }
}