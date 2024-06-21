package carbonneutral.academy.api.service.Receipt;

public enum CafeList {
    STARBUCKS("스타벅스 인하대점"),
    BLUEPOT("인하대 블루포트")
    ;

    private final String locationName;

    CafeList(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
    }
}
