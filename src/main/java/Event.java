public class Event extends Task{
    private String eventStart;
    private String eventEnd;
    public Event(String description){
        super(description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public String getDescription(){
        return "[T]" + super.getDescription();
    }
}