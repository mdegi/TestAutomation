package las3007.task1.factory.home;

import java.util.Arrays;
import java.util.Optional;

public enum RatingFilterEnum {
    YEARS_ALL ("All Ages"),
    YEARS_18_LESS ("<18"),
    YEARS_18_TO_29 ("18-29"),
    YEARS_30_TO_44 ("30-44"),
    YEARS_45_PLUS ("45+");

    private final String description;

    RatingFilterEnum(String description) {
        this.description = description;
    }

    public static RatingFilterEnum getRatingFilter(String dsc) {
        RatingFilterEnum ratingFilter = null;

        Optional<RatingFilterEnum> ratingFilterOpt = Arrays.stream(
                RatingFilterEnum.values())
                    .filter(g -> g.description.equals(dsc))
                    .findFirst();
        if (ratingFilterOpt.isPresent()) {
            ratingFilter = ratingFilterOpt.get();
        }
        return ratingFilter;
    }
}
