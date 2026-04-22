package com.integratedbookinfo.system.shared.enums;

public enum StoreStatus {

	OPEN("open", "営業中"),
	TAKING_INVENTORY("taking_inventory", "棚卸中"),
	TEMP_CLOSED("temp_closed", "臨時休業");
	
	private final String code;
	private final String label;
	
	private StoreStatus(String code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getStoreStatus() {
		return label;
	}
	
    public static StoreStatus fromCode(String code) {
        for (StoreStatus status : StoreStatus.values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid StoreStatus code: " + code);
    }
}
