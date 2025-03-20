package ru.gitmaxlla.itandp;

import java.util.Scanner;

public final class Bed {
    private static Scanner scanner = new Scanner(System.in);


    private boolean isDouble, isVertical;
    private double defaultPillowHeight;

    private static int objCounter = 0;

    private double width, height, depth;
    private double  mass, hardness;

    private String name;

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
    private USE_CASE useCase;
    private CONDITION condition;

    private Bed(boolean inputBedParams) {
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
        setCondition(Bed.CONDITION.valueOf(scanner.nextLine()));

        setUseCase(USE_CASE.LYING);

        if (inputBedParams) {
            System.out.print("	Is it double: ");
            setIsDouble(scanner.nextBoolean());

            if(isDouble) {
                System.out.print("	Beds are vertical: ");
                setIsVertical(scanner.nextBoolean());
            }
            else
            {
                setIsVertical(false);
            }

            System.out.print("	Factory pillow(s) height: ");
            setDefaultPillowHeight(scanner.nextDouble());
        }
    }

    private Bed(double width, double height, double depth, double price, double mass, double hardness, String name, USE_CASE use_case, CONDITION condition) {
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

    public Bed(double width, double height, double depth, double price, double mass, double hardness, String name, CONDITION condition, boolean isDouble, boolean isVertical, double defaultPillowHeight) {
        this(width, height, depth, price, mass, hardness, name, USE_CASE.LYING, condition);

        setDefaultPillowHeight(defaultPillowHeight);
        setIsDouble(isDouble);
        setIsVertical(isVertical);

        System.out.println("	Double bed: " + isDouble());
        if (isDouble) System.out.println("		 Beds are on top of each other: " + isVertical());
        System.out.println("	Factory pillow(s) height: " + getDefaultPillowHeight());
    }

    public Bed() {
        this(true);
    }

    public Bed(boolean isDouble, boolean isVertical, double defaultPillowHeight) {
        this(false);

        setIsDouble(isDouble);
        setIsVertical(isVertical);
        setDefaultPillowHeight(defaultPillowHeight);

        System.out.println("	Double bed: " + isDouble());
        if (isDouble) System.out.println("		 Beds are on top of each other: " + isVertical());
        System.out.println("	Factory pillow(s) height: " + getDefaultPillowHeight());
    }

    public void setIsDouble(boolean value) {
        isDouble = value;
    }

    public boolean isDouble() {
        return isDouble;
    }

    public void setIsVertical(boolean value) {
        isVertical = value;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public void setDefaultPillowHeight(double value) {
        defaultPillowHeight = value;
    }

    public double getDefaultPillowHeight() {
        return defaultPillowHeight;
    }

    public String getType() {
        if (!isDouble)
        {
            return "Single";
        }
        else
        {
            if (isVertical) {
                return "Double Vertical";
            }

            return "Double Horizontal";
        }
    }

    public boolean pillowsAreComfortable(double shoulder_length) {
        return (Math.abs(defaultPillowHeight - shoulder_length) < 3.5);
    }

    private void appendInfo() {
        System.out.println("	Price: " + getPrice());
        System.out.println("	Condition: " + getCondition());
        System.out.println("	Use case: " + getUseCase());
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

    public Bed.CONDITION getCondition() {
        return condition;
    }

    public void setUseCase(Bed.USE_CASE value) {
        useCase = value;
    }

    public Bed.USE_CASE getUseCase() {
        return  useCase;
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
        return getMass() * getVolume();
    }

    private void creationMessage() {
        System.out.println("@ Bed / obj no. " + getObjectsTotal());
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double value) {
        width = value;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double value) {
        height = value;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double value) {
        depth = value;
    }

    public double getHardness() {
        return hardness;
    }

    public void setHardness(double value) {
        hardness = value;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double value) {
        mass = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        name = value;
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

    public double priceFor(int n, CONDITION condition) {
        double coefficient = 1.0;

        switch (condition) {
            case USED -> coefficient = 0.7;
            case ANTIQUE -> coefficient = 10.0;
            case STORE_SHELF -> coefficient = 0.95;
        }

        double price =  n * getPrice() * coefficient;

        if (condition == CONDITION.ANTIQUE && isDouble) price *= 2.0;

        return price;
    }
}
