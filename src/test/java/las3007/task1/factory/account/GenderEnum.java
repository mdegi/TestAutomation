package las3007.task1.factory.account;

import java.util.Arrays;
import java.util.Optional;

public enum GenderEnum {
    MALE ("Male"),
    FEMALE ("Female");

    private final String description;

    GenderEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static GenderEnum getGender(String dsc) {
        GenderEnum gender = null;

        Optional<GenderEnum> genderByOpt = Arrays.stream(
                GenderEnum.values())
                    .filter(g -> g.description.equals(dsc))
                    .findFirst();
        if (genderByOpt.isPresent()) {
            gender = genderByOpt.get();
        }
        return gender;
    }
}
