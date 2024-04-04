package carbonneutral.academy.container.entity.enums;

public enum ContainerStatus {


    AVAILABLE("사용 가능"),
    READY("준비 상태"),
    IN_USE("사용중");
    private final String description;

    ContainerStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
