public class Event extends Task {
    private String eventStart;
    private String eventEnd;
    public Event(String description, String eventStart, String eventEnd) {
        super(description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    public static Event createEvent(String description) {
        try {
            String[] descriptionParts = description.split("( /from )|( /to )", 3);
            String event = descriptionParts[0].trim();
            String eventStart = descriptionParts[1].trim();
            String eventEnd = descriptionParts[2].trim();
            
            return new Event(event, eventStart, eventEnd);
        } catch (IndexOutOfBoundsException e) {
            Ui.say("Wrong format for Event.");
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + " (from: " + eventStart + "to: " + eventEnd + ")";
    }
}


