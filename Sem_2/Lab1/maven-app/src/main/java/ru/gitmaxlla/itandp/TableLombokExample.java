package ru.gitmaxlla.itandp;
import lombok.Getter;
import lombok.Setter;

public final class TableLombokExample extends Furniture {
    @Getter @Setter private boolean foldable;
    @Getter @Setter private double surfaceDurability;
    @Getter @Setter private int seatsNumber;

    public TableLombokExample(double width, double height, double depth, double price, double mass, double hardness, String name, CONDITION condition, boolean foldable, double surfaceDurability, int seatsNumber) {
        super(width, height, depth, price, mass, hardness, name, USE_CASE.BOTH, condition);

        setFoldable(foldable);
        setSurfaceDurability(surfaceDurability);
        setSeatsNumber(seatsNumber);

        System.out.println("	Table is foldable: " + isFoldable());
        System.out.println("	Number of seats available at table: " + getSeatsNumber());
        System.out.println("	Table's surface durability: " + getSurfaceDurability());
    }

    public TableLombokExample(boolean foldable, double surfaceDurability, int seatsNumber) {
        super(USE_CASE.BOTH);

        setFoldable(foldable);
        setSurfaceDurability(surfaceDurability);
        setSeatsNumber(seatsNumber);

        System.out.println("	Table is foldable: " + isFoldable());
        System.out.println("	Number of seats available at table: " + getSeatsNumber());
        System.out.println("	Table's surface durability: " + getSurfaceDurability());
    }

    public TableLombokExample() {
        super(USE_CASE.BOTH);

        System.out.print("	Is it foldable: ");
        setFoldable(scanner.nextBoolean());

        System.out.print("	Surface's durability: ");
        setSurfaceDurability(scanner.nextDouble());

        System.out.print("	Table's seats number: ");
        setSeatsNumber(scanner.nextInt());
    }

    public double durabilityPerSeat() {
        return surfaceDurability / seatsNumber;
    }

    public int minSeats() {
        return isFoldable() ? 1 : seatsNumber;
    }

    public double getSustainabilityScore() {
        return durabilityPerSeat() * getMass() * getHardness();
    }

    @Override
    protected void creationMessage() {
        System.out.println("@ Table / obj no. " + getObjectsTotal());
    }

    @Override
    public double priceFor(int n, CONDITION condition) {
        double price = super.priceFor(n, condition);

        if(condition == CONDITION.STORE_SHELF) {
            price *= 0.74; // To make it cost nearly as CONDITION.USED.
        }

        return price;
    }
}
