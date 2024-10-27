import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DataProcessor {
    public enum Type {
        TRANSFORM,
        FILTER,
        AGGREGATE,
        UNSPECIFIED,
        IGNORE,
        SEARCH,
        OTHER
    };

    Type value() default Type.UNSPECIFIED;
}
