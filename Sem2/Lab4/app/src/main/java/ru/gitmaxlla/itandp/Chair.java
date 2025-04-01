package ru.gitmaxlla.itandp;

import lombok.Getter;
import lombok.Setter;
import java.util.Scanner;


@Getter @Setter
public final class Chair implements Furniturable {
    private static final Scanner scanner = new Scanner(System.in);
    private static int objCounter = 0;

    private int legsNumber;
    private double backHeight;
    private boolean rollable;

    private double width, height, depth;
    private double  mass, hardness;
    private double price;

    private String name;
    private USE_CASE useCase;
    private CONDITION condition;


    private Chair(boolean inputChairParams) {
        ++objCounter;
        creationMessage();

        System.out.print("	Name: ");
        setName(scanner.nextLine());

        System.out.print("	Width: ");
        setWidth(scanner.nextDouble());

        System.out.print("	Height: ");
        setHeight(scanner.nextDouble());

        System.out.print("	Depth: ");
        setDepth(scanner.nextDouble());

        System.out.print("	Mass: ");
        setMass(scanner.nextDouble());

        System.out.print("	Hardness: ");
        setHardness(scanner.nextDouble());

        System.out.print("	Price: ");
        setPrice(scanner.nextDouble());
        scanner.nextLine();

        System.out.print("	Condition: ");
        setCondition(CONDITION.valueOf(scanner.nextLine()));

        setUseCase(USE_CASE.SITTING);

        if (inputChairParams) {
            System.out.print("	Is it rollable: ");
            setRollable(scanner.nextBoolean());

            System.out.print("	Number of legs: ");
            setLegsNumber(scanner.nextInt());

            System.out.print("	Chair's back height: ");
            setBackHeight(scanner.nextDouble());
        }
    }

    private Chair(double width, double height, double depth, double price, double mass, double hardness, String name, USE_CASE use_case, CONDITION condition) {
        objCounter++;

        setWidth(width);
        setHeight(height);
        setDepth(depth);
        setMass(mass);
        setHardness(hardness);
        setName(name);

        creationMessage();
        System.out.println("	Dimensions: " + getWidth() + " x " + getHeight() + " x " + getDepth());
        System.out.println("	Mass: " + getMass());
        System.out.println("	Hardness: " + getHardness());
        System.out.println("	Object's name: " + getName());

        setPrice(price);
        setCondition(condition);
        setUseCase(use_case);

        appendInfo();
    }

    public Chair(boolean rollable, int legs_number, double backHeight) {
        this(false);

        setRollable(rollable);
        setBackHeight(backHeight);
        setLegsNumber(legs_number);

        System.out.println("	Chair is rollable: " + isRollable());
        System.out.println("	Number of legs: " + getLegsNumber());
        System.out.println("	Chair's back height: " + getBackHeight());
    }

    public Chair() {
        this(true);
    }

    public Chair(double width, double height, double depth, double price, double mass, double hardness, String name, CONDITION condition, boolean rollable, int legs_number, double backHeight) {
        this(width, height, depth, price, mass, hardness, name, USE_CASE.SITTING, condition);

        setRollable(rollable);
        setBackHeight(backHeight);
        setLegsNumber(legs_number);

        System.out.println("	Chair is rollable: " + isRollable());
        System.out.println("	Number of legs: " + getLegsNumber());
        System.out.println("	Chair's back height: " + getBackHeight());
    }

    public String describeUseCase() {
        String result = "";

        switch (useCase) {
            case UNSPECIFIED -> result = "This furniture's preferred use case hasn't been defined yet.";
            case OTHER -> result = "This furniture's use case can't be defined either as for sitting or for lying.";
            case SITTING -> result = "This piece of furniture is used to sit on it.";
            case LYING -> result = "This piece of furniture can be lied upon.";
            case BOTH -> result = "This piece of furniture is suited both for lying and for sitting.";
        }

        return result;
    }

    public double getImmobilityScore() {
        double score = getMass() * getVolume();
        if (isRollable()) score /= 2;
        return score;
    }

    private void creationMessage() {
        System.out.println("@ Chair / obj no. " + getObjectsTotal());
    }

    private void appendInfo() {
        System.out.println("	Price: " + getPrice());
        System.out.println("	Condition: " + getCondition());
        System.out.println("	Use case: " + getUseCase());
    }

    public double getVolume() {
        return width * height * depth;
    }

    public double averageDensity() {
        return (mass / getVolume());
    }

    public static int getObjectsTotal() {
        return objCounter;
    }

    public double priceFor(int n) {
        double coefficient = 1.0;
        CONDITION condition = getCondition();

        switch (condition) {
            case USED -> coefficient = 0.7;
            case ANTIQUE -> coefficient = 10.0;
            case STORE_SHELF -> coefficient = 0.95;
        }

        return n * price * coefficient;
    }

    public double getStabilityScore() {
        double backHeightAdjusted = getBackHeight();
        double rollableScoreModifier = 1.0;

        if (getBackHeight() == 0) backHeightAdjusted = 0.01;
        if (isRollable()) rollableScoreModifier = 0.7;

        return legsNumber * (1 / backHeightAdjusted) * rollableScoreModifier;
    }
    public double getSeatHeight() {
        return getHeight() - getBackHeight();
    }
}

