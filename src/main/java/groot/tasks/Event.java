package groot.tasks;

import groot.ui.Ui;

public class Event extends Task {
    private String eventStart;
    private String eventEnd;

    public Event(String description, String eventStart, String eventEnd, String key) {
        super(description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        taskType = "E";
        this.key = key;
    }

    /** 
     * Method that to create Event.
     * Parses user input and creates Event.
     * returns null if wrong format.
     * @param description unparsed Event.
     */     
    

    public static Event createEvent(String description) {
        try {
            String[] descriptionParts = description.split("( /from )|( /to )", 3);
            String event = descriptionParts[0].trim();
            String eventStart = descriptionParts[1].trim();
            String eventEnd = descriptionParts[2].trim();
            
            // if(event.isBlank() || eventStart.isBlank() || eventEnd.isBlank()){
            //     Ui.say("Wrong format for Event!" + Ui.NEW_LINE +
            //         Ui.INDENT + "Use the format {Event /from Start Date /to End Date}");
            //     return null;
            // }

            return new Event(event, eventStart, eventEnd, description);
        } catch (IndexOutOfBoundsException e) {
            Ui.say("Wrong format for Event!" + Ui.NEW_LINE +
                Ui.INDENT + "Use the format {Event /from Start Date /to End Date}");
            return null;
        }
    }

    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + " (from: " + eventStart + " to: " + eventEnd + ")";
    }
}


