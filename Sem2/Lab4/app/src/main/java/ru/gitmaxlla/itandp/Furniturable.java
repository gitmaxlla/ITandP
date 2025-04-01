package ru.gitmaxlla.itandp;

public interface Furniturable {
    enum USE_CASE {
        UNSPECIFIED,
        SITTING,
        LYING,
        BOTH,
        OTHER;
    };

    enum CONDITION {
        UNSPECIFIED,
        ANTIQUE,
        USED,
        STORE_SHELF,
        BRAND_NEW,
        PROTOTYPE
    }

    private void creationMessage() {
        System.out.println("@ Furniture / obj no. " + getObjectsTotal());
    }

    static int getObjectsTotal() {
        return -1;
    }

    void setPrice(double value);
    double getPrice();

    void setCondition(CONDITION value);
    CONDITION getCondition();

    void setUseCase(USE_CASE value);
    USE_CASE getUseCase();

    double priceFor(int n);

    String describeUseCase();

    double getImmobilityScore();

    double getWidth();
    void setWidth(double value);

    double getHeight();
    void setHeight(double value);

    double getDepth();
    void setDepth(double value);

    double getHardness();
    void setHardness(double value);

    double getMass();
    void setMass(double value);

    String getName();
    void setName(String value);

    double getVolume();
    double averageDensity();
}
