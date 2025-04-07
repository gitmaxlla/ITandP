package ru.gitmaxlla.itandp;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.util.Scanner;

@EqualsAndHashCode
@Getter @Setter
public final class Bed implements Furniturable {
    private static final Scanner scanner = new Scanner(System.in);
    private static int objCounter = 0;

    private boolean isDouble, isVertical;
    private double defaultPillowHeight;

    private double width, height, depth;
    private double  mass, hardness;
    private double price;

    private String name;
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
            setDouble(scanner.nextBoolean());

            if(isDouble) {
                System.out.print("	Beds are vertical: ");
                setVertical(scanner.nextBoolean());
            }
            else
            {
                setVertical(false);
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
        setDouble(isDouble);
        setVertical(isVertical);

        System.out.println("	Double bed: " + isDouble());
        if (isDouble) System.out.println("		 Beds are on top of each other: " + isVertical());
        System.out.println("	Factory pillow(s) height: " + getDefaultPillowHeight());
    }

    public Bed() {
        this(true);
    }

    public Bed(boolean isDouble, boolean isVertical, double defaultPillowHeight) {
        this(false);

        setDouble(isDouble);
        setVertical(isVertical);
        setDefaultPillowHeight(defaultPillowHeight);

        System.out.println("	Double bed: " + isDouble());
        if (isDouble) System.out.println("		 Beds are on top of each other: " + isVertical());
        System.out.println("	Factory pillow(s) height: " + getDefaultPillowHeight());
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

        double price =  n * getPrice() * coefficient;

        if (condition == CONDITION.ANTIQUE && isDouble) price *= 2.0;

        return price;
    }
}
