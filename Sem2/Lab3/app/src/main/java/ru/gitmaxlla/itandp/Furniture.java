package ru.gitmaxlla.itandp;

import java.util.Scanner;

public class Furniture {
    private static Scanner scanner = new Scanner(System.in);
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

    private Furniture() {
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
    }

    public Furniture(double width, double height, double depth, double price, double mass, double hardness, String name, USE_CASE use_case, CONDITION condition) {
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

    public Furniture(double price, USE_CASE use_case, CONDITION condition) {
        this();

        setPrice(price);
        setCondition(condition);
        setUseCase(use_case);

        System.out.println("Furniture's append info");
        appendInfo();
    }

    public Furniture(USE_CASE presetUseCase) {
        this();

        System.out.print("	Price: ");
        setPrice(scanner.nextDouble());
        scanner.nextLine();

        System.out.print("	Condition: ");
        setCondition(CONDITION.valueOf(scanner.nextLine()));

        if(presetUseCase == USE_CASE.UNSPECIFIED) {
            System.out.print("	Use case: ");
            setUseCase(USE_CASE.valueOf(scanner.nextLine()));
        }
        else
        {
            setUseCase(presetUseCase);
        }
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

    public CONDITION getCondition() {
        return condition;
    }

    public void setUseCase(USE_CASE value) {
        useCase = value;
    }

    public USE_CASE getUseCase() {
        return  useCase;
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
        System.out.println("@ Furniture / obj no. " + getObjectsTotal());
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
}
