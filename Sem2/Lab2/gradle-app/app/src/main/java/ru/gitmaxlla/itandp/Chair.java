package ru.gitmaxlla.itandp;

public final class Chair extends Furniture {
    private int legs;
    private double backHeight;
    private boolean rollable;

    public Chair(double width, double height, double depth, double price, double mass, double hardness, String name, CONDITION condition, boolean rollable, int legs_number, double backHeight) {
        super(width, height, depth, price, mass, hardness, name, USE_CASE.SITTING, condition);

        setRollable(rollable);
        setBackHeight(backHeight);
        setLegsNumber(legs_number);

        System.out.println("	Chair is rollable: " + isRollable());
        System.out.println("	Number of legs: " + getLegsNumber());
        System.out.println("	Chair's back height: " + getBackHeight());
    }

    public Chair(boolean rollable, int legs_number, double backHeight) {
        super(USE_CASE.SITTING);

        setRollable(rollable);
        setBackHeight(backHeight);
        setLegsNumber(legs_number);

        System.out.println("	Chair is rollable: " + isRollable());
        System.out.println("	Number of legs: " + getLegsNumber());
        System.out.println("	Chair's back height: " + getBackHeight());
    }

    public Chair() {
        super(USE_CASE.SITTING);

        System.out.print("	Is it rollable: ");
        setRollable(scanner.nextBoolean());

        System.out.print("	Number of legs: ");
        setLegsNumber(scanner.nextInt());

        System.out.print("	Chair's back height: ");
        setBackHeight(scanner.nextDouble());
    }

    public void setRollable(boolean value) {
        rollable = value;
    }

    public boolean isRollable() {
        return rollable;
    }

    public void setLegsNumber(int value) {
        legs = value;
    }

    public int getLegsNumber() {
        return legs;
    }

    public void setBackHeight(double value) {
        backHeight = value;
    }

    public double getBackHeight() {
        return backHeight;
    }

    public double getStabilityScore() {
        double backHeightAdjusted = getBackHeight();
        double rollableScoreModifier = 1.0;

        if (getBackHeight() == 0) backHeightAdjusted = 0.01;
        if (isRollable()) rollableScoreModifier = 0.7;

        return legs * (1 / backHeightAdjusted) * rollableScoreModifier;
    }

    public double getSeatHeight() {
        return getHeight() - getBackHeight();
    }

    @Override
    protected void creationMessage() {
        System.out.println("@ Chair / obj no. " + getObjectsTotal());
    }

    @Override
    public double getImmobilityScore() {
        double score = super.getImmobilityScore();
        if (isRollable()) score /= 2;
        return score;
    }
}
