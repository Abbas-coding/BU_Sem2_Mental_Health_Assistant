package source.mentalhealthassistant.core;

public class MindfulExercise extends CopingMechanism{
    private int duration;

    public MindfulExercise(String name, String description, int duration) {
        super(name, description);
        this.duration = duration;
    }

    public void performActivity() {
        // To be implemented later
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
