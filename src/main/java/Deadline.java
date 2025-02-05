public class Deadline extends Task {
    private String deadlineDue;
    public Deadline(String description, String deadlineDue) {
        super(description);
        this.deadlineDue = deadlineDue;
    }

    public static Deadline createDeadline(String description) {
        try {
            String[] descriptionParts = description.split(" /by ", 2);
            String deadline = descriptionParts[0].trim();
            String deadlineDue = descriptionParts[1].trim();

            return new Deadline(deadline, deadlineDue);
        } catch (IndexOutOfBoundsException e) {
            Ui.say("Wrong format for Deadline.");
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + " (by: " + deadlineDue + ")";
    }
}