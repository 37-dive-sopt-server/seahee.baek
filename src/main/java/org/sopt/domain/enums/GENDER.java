package org.sopt.domain.enums;

public enum GENDER {
	MALE("남자"),
	FEMALE("여자");

	private final String gender;

	GENDER(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public static GENDER fromString(String input) {
		if (input.equals("남자")) { return GENDER.MALE; }
		else {return GENDER.FEMALE; }
	}
}
