package source.mentalhealthassistant.core;

public abstract class CopingMechanism {
    private String name;
    private String description;

    public CopingMechanism(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void performActivity();
        // To be implemented by subclasses

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
