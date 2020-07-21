package las3007.task1.factory.search;

import java.util.Arrays;
import java.util.Optional;

public enum SearchGroupsEnum {
    IMDB_TOP_100 ("IMDB Top 100", "groups-1"),
    IMDB_TOP_250 ("IMDB Top 250", "groups-2"),
    IMDB_BOTTOM_1000 ("IMDB Top 1000", "groups-50");

    private final String description;
    private final String id;

    SearchGroupsEnum(String description, String id) {
        this.description = description;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static SearchGroupsEnum getSearchGroup(String dsc) {
        SearchGroupsEnum searchGroup = null;

        Optional<SearchGroupsEnum> groupOpt = Arrays.stream(
                SearchGroupsEnum.values())
                    .filter(g -> g.description.equals(dsc))
                    .findFirst();
        if (groupOpt.isPresent()) {
            searchGroup = groupOpt.get();
        }
        return searchGroup;
    }
}
