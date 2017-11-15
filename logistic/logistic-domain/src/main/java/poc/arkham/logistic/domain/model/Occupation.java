package poc.arkham.logistic.domain.model;

import java.time.LocalDate;

public class Occupation {

    private final String inmateId;

    private final LocalDate start;

    private final LocalDate end;

    public Occupation(String inmateId, LocalDate start, LocalDate end) {
        this.inmateId = inmateId;
        this.start = start;
        this.end = end;
    }

    public String getInmateId() {
        return inmateId;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}
