package las3007.task1.factory.search;

import java.util.Arrays;
import java.util.Optional;

public enum OrderByEnum {
    Popularity_Ascending ("Popularity Ascending"),
    A_Z_Ascending ("A-Z Ascending"),
    A_Z_Descending ("A-Z Descending");

    private final String description;

    private OrderByEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static OrderByEnum getOrderBy(String dsc) {
        OrderByEnum orderBy = null;

        Optional<OrderByEnum> orderByOpt = Arrays.stream(
                OrderByEnum.values())
                    .filter(g -> g.description.equals(dsc))
                    .findFirst();
        if (orderByOpt.isPresent()) {
            orderBy = orderByOpt.get();
        }
        return orderBy;
    }
}
