package webservice.BHXH.enums;

import java.util.HashMap;
import java.util.Map;

public enum Role {
	ADMIN(0), USER(1);
	private final int value;
	private static Map<Integer, Role> map = new HashMap<Integer, Role>();

	private Role(int value) {
		this.value = value;
	}

	static {
		for (Role role : Role.values()) {
			map.put(role.value, role);
		}
	}

	public static Role valueOf(int value) {
		return map.get(value);
	}

	public int getValue() {
		return value;
	}
}
