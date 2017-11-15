package poc.arkham.logistic.domain.model;

import java.time.LocalDate;

public class Maintenance {

    private final LocalDate start;

    private final LocalDate end;

    public Maintenance(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}
