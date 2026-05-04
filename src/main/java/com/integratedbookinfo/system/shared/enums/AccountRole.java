package com.integratedbookinfo.system.shared.enums;

public enum AccountRole {

	ADMIN("admin", "管理者"),
	STORE("store", "書店");

	
	private final String code;
	private final String label;
	
	private AccountRole(String code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
    public static AccountRole fromCode(String code) {
        for (AccountRole status : AccountRole.values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid StoreStatus code: " + code);
    }
}
