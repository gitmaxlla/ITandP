package ru.gitmaxlla.itandp;

import lombok.Getter;
import lombok.Setter;
import java.util.Scanner;


@Setter @Getter
public final class Table implements Furniturable {
    private static final Scanner scanner = new Scanner(System.in);
    private static int objCounter = 0;

    private boolean foldable;
    private double surfaceDurability;
    private int seatsNumber;
    private double width, height, depth;
    private double  mass, hardness;
    private String name;
    private double price;
    private USE_CASE useCase;
    private CONDITION condition;

    private Table(boolean inputTableParams) {
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

        setUseCase(USE_CASE.BOTH);

        if (inputTableParams) {
            System.out.print("	Is it foldable: ");
            setFoldable(scanner.nextBoolean());

            System.out.print("	Surface's durability: ");
            setSurfaceDurability(scanner.nextDouble());

            System.out.print("	Table's seats number: ");
            setSeatsNumber(scanner.nextInt());
        }
    }

    private Table(double width, double height, double depth, double price, double mass, double hardness, String name, CONDITION condition) {
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
        setUseCase(USE_CASE.BOTH);

        appendInfo();
    }


    public Table(double width, double height, double depth, double price, double mass, double hardness, String name, CONDITION condition, boolean foldable, double surfaceDurability, int seatsNumber) {
        this(width, height, depth, price, mass, hardness, name, condition);

        setFoldable(foldable);
        setSurfaceDurability(surfaceDurability);
        setSeatsNumber(seatsNumber);

        System.out.println("	Table is foldable: " + isFoldable());
        System.out.println("	Number of seats available at table: " + getSeatsNumber());
        System.out.println("	Table's surface durability: " + getSurfaceDurability());
    }

    public Table(boolean foldable, double surfaceDurability, int seatsNumber) {
        this(false);

        setFoldable(foldable);
        setSurfaceDurability(surfaceDurability);
        setSeatsNumber(seatsNumber);

        System.out.println("	Table is foldable: " + isFoldable());
        System.out.println("	Number of seats available at table: " + getSeatsNumber());
        System.out.println("	Table's surface durability: " + getSurfaceDurability());
    }

    public Table() {
        this(true);
    }

    private void appendInfo() {
        System.out.println("	Price: " + getPrice());
        System.out.println("	Condition: " + getCondition());
        System.out.println("	Use case: " + getUseCase());
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
        System.out.println("@ Table / obj no. " + getObjectsTotal());
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

    public double durabilityPerSeat() {
        return surfaceDurability / seatsNumber;
    }

    public int minSeats() {
        return isFoldable() ? 1 : seatsNumber;
    }

    public double priceFor(int n) {
        double coefficient = 1.0;
        CONDITION condition = getCondition();

        switch (condition) {
            case USED -> coefficient = 0.7;
            case ANTIQUE -> coefficient = 10.0;
            case STORE_SHELF -> coefficient = 0.95;
        }

        double price =  n * getPrice() * coefficient;

        if(condition == CONDITION.STORE_SHELF) {
            price *= 0.74; // To make it cost nearly as CONDITION.USED.
        }

        return price;
    }

    public double getSustainabilityScore() {
        return durabilityPerSeat() * getMass() * getHardness();
    }
}
