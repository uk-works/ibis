package com.integratedbookinfo.system.shared.enums;

public enum InventoryStatus {

	ACTIVE("active", "販売中"),
	RESTOCK_PLANNED("restock_planned", "再入荷予定"),
	RESTOCK_UNDECIDED("restock_undecided", "再入荷未定"),
	DISCONTINUED("discontinued", "取扱休止");
;

	
	private final String code;
	private final String label;
	
	private InventoryStatus(String code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
    public static InventoryStatus fromCode(String code) {
        for (InventoryStatus status : InventoryStatus.values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid StoreStatus code: " + code);
    }
}
