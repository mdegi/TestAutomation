package las3007.task1.factory.search;

import java.util.Arrays;
import java.util.Optional;

public enum DisplayPerPageEnum {
    FIFTY ("50 per page", 50),
    ONE_HUNDRED ("100 per page", 100),
    TWO_HUNDRED_FIFTY ("250 per page", 250);

    private final String description;
    private final int qtyPerPage;

    DisplayPerPageEnum(String description, int qtyPerPage) {
        this.description = description;
        this.qtyPerPage = qtyPerPage;
    }

    public String getDescription() {
        return description;
    }

    public static DisplayPerPageEnum getDisplayPerPage(int qty) {
        DisplayPerPageEnum displayPerPageEnum = null;

        Optional<DisplayPerPageEnum> displayOpt = Arrays.stream(
                DisplayPerPageEnum.values())
                .filter(dsp -> dsp.qtyPerPage == qty)
                .findFirst();
        if (displayOpt.isPresent()) {
            displayPerPageEnum = displayOpt.get();
        }
        return displayPerPageEnum;
    }

}
