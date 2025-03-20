public final class Table extends Furniture {
    private boolean foldable;
    private double surfaceDurability;
    private int seats;

    public Table(double width, double height, double depth, double price, double mass, double hardness, String name, CONDITION condition, boolean foldable, double surfaceDurability, int seatsNumber) {
        super(width, height, depth, price, mass, hardness, name, USE_CASE.BOTH, condition);

        setFoldable(foldable);
        setSurfaceDurability(surfaceDurability);
        setSeatsNumber(seatsNumber);

        System.out.println("\tTable is foldable: " + isFoldable());
        System.out.println("\tNumber of seats available at table: " + getSeatsNumber());
        System.out.println("\tTable's surface durability: " + getSurfaceDurability());
    }

    public Table(boolean foldable, double surfaceDurability, int seatsNumber) {
        super(USE_CASE.BOTH);

        setFoldable(foldable);
        setSurfaceDurability(surfaceDurability);
        setSeatsNumber(seatsNumber);

        System.out.println("\tTable is foldable: " + isFoldable());
        System.out.println("\tNumber of seats available at table: " + getSeatsNumber());
        System.out.println("\tTable's surface durability: " + getSurfaceDurability());
    }

    public Table() {
        super(USE_CASE.BOTH);

        System.out.print("\tIs it foldable: ");
        setFoldable(scanner.nextBoolean());

        System.out.print("\tSurface's durability: ");
        setSurfaceDurability(scanner.nextDouble());

        System.out.print("\tTable's seats number: ");
        setSeatsNumber(scanner.nextInt());
    }

    public void setFoldable(boolean value) {
        foldable = value;
    }

    public boolean isFoldable() {
        return foldable;
    }

    public void setSeatsNumber(int value) {
        seats = value;
    }

    public int getSeatsNumber() {
        return seats;
    }

    public void setSurfaceDurability(double value) {
        surfaceDurability = value;
    }

    public double  getSurfaceDurability() {
        return surfaceDurability;
    }

    public double durabilityPerSeat() {
        return surfaceDurability / seats;
    }

    public int minSeats() {
        return isFoldable() ? 1 : seats;
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
