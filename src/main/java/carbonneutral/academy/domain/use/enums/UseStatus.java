package carbonneutral.academy.domain.use.enums;

public enum UseStatus {

    USING("이용중"),
    RETURNED("반납완료"),
    CANCELED("이용취소"),
    EXPIRED("기간지남");

    private final String description;

    UseStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
