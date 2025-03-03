package ru.gitmaxlla.itandp;

public final class Bed extends Furniture {
    private boolean isDouble, isVertical;
    private double defaultPillowHeight;

    public Bed(double width, double height, double depth, double price, double mass, double hardness, String name, CONDITION condition, boolean isDouble, boolean isVertical, double defaultPillowHeight) {
        super(width, height, depth, price, mass, hardness, name, USE_CASE.LYING, condition);

        setDefaultPillowHeight(defaultPillowHeight);
        setIsDouble(isDouble);
        setIsVertical(isVertical);

        System.out.println("	Double bed: " + isDouble());
        if (isDouble) System.out.println("		 Beds are on top of each other: " + isVertical());
        System.out.println("	Factory pillow(s) height: " + getDefaultPillowHeight());
    }

    public Bed(boolean isDouble, boolean isVertical, double defaultPillowHeight) {
        super(USE_CASE.LYING);

        setIsDouble(isDouble);
        setIsVertical(isVertical);
        setDefaultPillowHeight(defaultPillowHeight);

        System.out.println("	Double bed: " + isDouble());
        if (isDouble) System.out.println("		 Beds are on top of each other: " + isVertical());
        System.out.println("	Factory pillow(s) height: " + getDefaultPillowHeight());
    }

    public Bed() {
        super(USE_CASE.LYING);

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

    @Override
    protected void creationMessage() {
        System.out.println("@ Bed / obj no. " + getObjectsTotal());
    }

    @Override
    public double priceFor(int n, CONDITION condition) {
        double price = super.priceFor(n, condition);
        if (condition == CONDITION.ANTIQUE && isDouble) price *= 2.0;
        return price;
    }
}
