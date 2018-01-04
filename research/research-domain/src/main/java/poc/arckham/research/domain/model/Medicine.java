package poc.arckham.research.domain.model;

public class Medicine {

    private String id;

    private String label;

    private MedicineState state;

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public MedicineState getState() {
        return state;
    }

    public void setState(MedicineState state) {
        this.state = state;
    }

    public static class MedicineBuilder
    {
        private Medicine medicine;

        private MedicineBuilder()
        {
            medicine = new Medicine();
        }

        public MedicineBuilder id(String id)
        {
            medicine.id = id;
            return this;
        }

        public MedicineBuilder label(String label)
        {
            medicine.label = label;
            return this;
        }

        public MedicineBuilder state(MedicineState state)
        {
            medicine.state = state;
            return this;
        }

        public static MedicineBuilder newMedicine()
        {
            return new MedicineBuilder();
        }

        public Medicine build()
        {
            return medicine;
        }
    }
}
