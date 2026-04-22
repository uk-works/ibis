package com.integratedbookinfo.system.shared.enums;

public enum ProposalStatus {

	APPROVE("approve", "承認"),
	REJECT("reject", "却下");

	
	private final String code;
	private final String label;
	
	private ProposalStatus(String code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getProposalStatus() {
		return label;
	}
	
    public static ProposalStatus fromCode(String code) {
        for (ProposalStatus status : ProposalStatus.values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid StoreStatus code: " + code);
    }
}
