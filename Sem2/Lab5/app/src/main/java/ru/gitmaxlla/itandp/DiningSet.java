package ru.gitmaxlla.itandp;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

@Getter @Setter
public class DiningSet {
    private HashMap<Furniturable, Integer> contents;
    private double setDiscount;

    public DiningSet(HashMap<Furniturable, Integer> contents) {
        this(contents, 0.0);
    }

    public DiningSet(HashMap<Furniturable, Integer> contents,
                     double setDiscount) {
        setContents(contents);
        setSetDiscount(setDiscount);
    }

    public double getPrice() {
        AtomicReference<Double> result = new AtomicReference<>((double) 0);

        contents.forEach(((item, amount) -> {
            result.updateAndGet(v -> ((v + item.priceFor(amount))));
        }));

        return result.get() * (1 - getSetDiscount());
    }
}
