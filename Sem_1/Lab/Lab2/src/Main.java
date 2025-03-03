public class Main {
    public static void main(String[] args) {
        Furniture obj_preset = new Furniture(123, 123, 123, 123, 123, 123, "123", Furniture.USE_CASE.SITTING, Furniture.CONDITION.UNSPECIFIED);
        Chair obj_chair = new Chair(123, 123, 123, 123, 123, 123, "Something", Furniture.CONDITION.UNSPECIFIED, false, 2, 130.0);
        System.out.println(obj_chair.getImmobilityScore());
    }
}