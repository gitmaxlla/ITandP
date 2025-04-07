package ru.gitmaxlla.itandp;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

@Getter @Setter
public class DiningSetAutowired {
    @Autowired @Qualifier("ContentsForDiningSetAutowired")
    private HashMap<Furniturable, Integer> contents;

    private double setDiscount;

    public DiningSetAutowired(double setDiscount) {
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
