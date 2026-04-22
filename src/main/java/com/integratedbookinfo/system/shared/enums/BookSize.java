package com.integratedbookinfo.system.shared.enums;

public enum BookSize {

	BUNKO("bunko", "文庫"), 
	SHINSHO("shinsho", "新書"), 
	TANKOBON("tankobon", "単行本"), 
	OGATA("ogata", "大型"), 
	MOOK("mook", "ムック"), 
	MAGAZINE("magazine", "雑誌");

	
	private final String code;
	private final String label;
	
	private BookSize(String code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getBookSize() {
		return label;
	}
	
    public static BookSize fromCode(String code) {
        for (BookSize status : BookSize.values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid StoreStatus code: " + code);
    }
}
