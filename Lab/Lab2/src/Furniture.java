public class Furniture extends PhysicalObject {
    public enum USE_CASE {
        UNSPECIFIED,
        SITTING,
        LYING,
        BOTH,
        OTHER;
    };

    public enum CONDITION {
        UNSPECIFIED,
        ANTIQUE,
        USED,
        STORE_SHELF,
        BRAND_NEW,
        PROTOTYPE
    }

    private double price;
    private USE_CASE use_case;
    private CONDITION condition;

    public Furniture(double width, double height, double depth, double price, double mass, double hardness, String name, USE_CASE use_case, CONDITION condition) {
        super(width, height, depth, mass, hardness, name);

        setPrice(price);
        setCondition(condition);
        setUseCase(use_case);

        appendInfo();
    }

    public Furniture(double price, USE_CASE use_case, CONDITION condition) {
        super();

        setPrice(price);
        setCondition(condition);
        setUseCase(use_case);

        System.out.println("Furniture's append info");
        appendInfo();
    }

    public Furniture(USE_CASE presetUseCase) {
        super();

        System.out.print("\tPrice: ");
        setPrice(scanner.nextDouble());
        scanner.nextLine();

        System.out.print("\tCondition: ");
        setCondition(CONDITION.valueOf(scanner.nextLine()));

        if(presetUseCase == USE_CASE.UNSPECIFIED) {
            System.out.print("\tUse case: ");
            setUseCase(USE_CASE.valueOf(scanner.nextLine()));
        }
        else
        {
            setUseCase(presetUseCase);
        }
    }

    protected void appendInfo() {
        System.out.println("\tPrice: " + getPrice());
        System.out.println("\tCondition: " + getCondition());
        System.out.println("\tUse case: " + getUseCase());
    }

    public void setPrice(double value) {
        price = value;
    }

    public double getPrice() {
        return price;
    }

    public void setCondition(CONDITION value) {
        condition = value;
    }

    public CONDITION getCondition() {
        return condition;
    }

    public void setUseCase(USE_CASE value) {
        use_case = value;
    }

    public USE_CASE getUseCase() {
        return  use_case;
    }

    public double priceFor(int n, CONDITION condition) {
        double coefficient = 1.0;

        switch (condition) {
            case USED -> coefficient = 0.7;
            case ANTIQUE -> coefficient = 10.0;
            case STORE_SHELF -> coefficient = 0.95;
        }

        return n * price * coefficient;
    }

    public String describeUseCase() {
        String result = "";

        switch (use_case) {
            case UNSPECIFIED -> result = "This furniture's preferred use case hasn't been defined yet.";
            case OTHER -> result = "This furniture's use case can't be defined either as for sitting or for lying.";
            case SITTING -> result = "This piece of furniture is used to sit on it.";
            case LYING -> result = "This piece of furniture can be lied upon.";
            case BOTH -> result = "This piece of furniture is suited both for lying and for sitting.";
        }

        return result;
    }

    public double getImmobilityScore() {
        return getMass() * getVolume();
    }

    @Override
    protected void creationMessage() {
        System.out.println("@ Furniture / obj no. " + getObjectsTotal());
    }
}
