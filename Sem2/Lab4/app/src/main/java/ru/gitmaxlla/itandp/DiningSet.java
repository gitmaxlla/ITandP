package ru.gitmaxlla.itandp;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter @Setter
public class DiningSet {
    private List<Map.Entry<Furniturable, Integer>> contents;
    private double setDiscount;

    public DiningSet(List<Map.Entry<Furniturable, Integer>> contents) {
        this(contents, 0.0);
    }

    public DiningSet(List<Map.Entry<Furniturable, Integer>> contents,
                     double setDiscount) {
        setContents(contents);
        setSetDiscount(setDiscount);
    }

    public double getPrice() {
        double result = 0;

        for (Map.Entry<Furniturable, Integer> furnitureAmount : contents) {
            Furniturable item = furnitureAmount.getKey();
            int amount = furnitureAmount.getValue();

            result += item.priceFor(amount);
        }

        return result * (1 - getSetDiscount());
    }
}
