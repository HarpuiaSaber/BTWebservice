package webservice.BHXH.enums;

public enum Bank {
    TECHCOMBANK(1), AGRIBANK(2), VIETCOMBANK(3), BIDV(4), VPBANK(5), TPBANK(6);
    private final int value;

    private Bank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
