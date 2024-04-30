package carbonneutral.academy.domain.location.enums;

public enum LocationType {
    CAFE("cafe"),
    RESTAURANT("restaurant"),
    RETURN("return");


    private final String description;

    LocationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
