import java.util.Scanner;

public abstract class PhysicalObject {
    public static Scanner scanner = new Scanner(System.in);
    private static int objCounter = 0;

    private double width, height, depth;
    private double  mass, hardness;

    private String name;

    public PhysicalObject(double width, double height, double depth, double mass, double hardness, String name) {
        objCounter++;

        setWidth(width);
        setHeight(height);
        setDepth(depth);
        setMass(mass);
        setHardness(hardness);
        setName(name);

        creationMessage();
        System.out.println("\tDimensions: " + getWidth() + " x " + getHeight() + " x " + getDepth());
        System.out.println("\tMass: " + getMass());
        System.out.println("\tHardness: " + getHardness());
        System.out.println("\tObject's name: " + getName());
    }

    public PhysicalObject(double width, double height, double depth) {
        this(width, height, depth, -1.0, -1.0, " ");
    }

    public PhysicalObject() {
        ++objCounter;
        creationMessage();

        System.out.print("\tName: ");
        setName(scanner.nextLine());

        System.out.print("\tWidth: ");
        setWidth(scanner.nextDouble());

        System.out.print("\tHeight: ");
        setHeight(scanner.nextDouble());

        System.out.print("\tDepth: ");
        setDepth(scanner.nextDouble());

        System.out.print("\tMass: ");
        setMass(scanner.nextDouble());

        System.out.print("\tHardness: ");
        setHardness(scanner.nextDouble());
    }

    protected void creationMessage() {
        System.out.println("@ Physical object / obj no. " + getObjectsTotal());
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
