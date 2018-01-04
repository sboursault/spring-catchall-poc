package poc.arkham.treatment.domain.model;

public class Medicine {

    private String id;

    private String label;

    public Medicine(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}
