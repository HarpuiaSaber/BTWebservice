package webservice.BHXH.enums;

public enum HumanObject {
	POOR(0), NEARPOOR(1), OTHER(2);
	private final int value;

	private HumanObject(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
