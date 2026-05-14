package com.integratedbookinfo.system.shared.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class CCodeUtil {
	private static final String[] TARGET_AUDIENCES = {"一般", "教養", "実用", "専門", "検定教科書消費税非課税品", "女性", "学参Ⅰ", "学参Ⅱ", "児童", "雑誌扱いコミックスおよびムック"};
	private static final String[] PUBLICATION_FORMATS = {"単行本", "文庫", "新書", "全集・双書", "その他ムック", "辞典・事典", "図鑑", "絵本", "電磁的記録媒体など", "コミックス"};
	
	private static final Map<String, String> CONTENT_MAP = initializeContentMap();

    private static Map<String, String> initializeContentMap() {

        Map<String, String> map = new LinkedHashMap<>();

        map.put("00", "総記");
        map.put("01", "百科事典");
        map.put("02", "年鑑雑誌");
        map.put("04", "情報科学");

        map.put("10", "哲学");
        map.put("11", "心理(学)");
        map.put("12", "倫理(学)");
        map.put("14", "宗教");
        map.put("15", "仏教");
        map.put("16", "キリスト教");
        
        map.put("20", "歴史総記");
        map.put("21", "日本歴史");
        map.put("22", "外国歴史");
        map.put("23", "伝記系譜");
        map.put("25", "地理");
        map.put("26", "旅行");

        map.put("30", "社会科学総記");
        map.put("31", "政治含む国防・軍事");
        map.put("32", "法律");
        map.put("33", "経済財政統計");
        map.put("34", "経営");
        map.put("36", "社会");
        map.put("37", "教育");
        map.put("39", "民族風習");

        map.put("40", "自然科学総記");
        map.put("41", "数学");
        map.put("42", "物理学");
        map.put("43", "化学");
        map.put("44", "天文地学");
        map.put("45", "生物学");
        map.put("47", "医学歯学薬学");

        map.put("50", "工学工業総記");
        map.put("51", "土木");
        map.put("52", "建築");
        map.put("53", "機械");
        map.put("54", "電気");
        map.put("55", "電子通信");
        map.put("56", "海事兵器");
        map.put("57", "採鉱治金");
        map.put("58", "各種工業");
        
        map.put("60", "産業総記");
        map.put("61", "農林業");
        map.put("62", "水産業");
        map.put("63", "商業");
        map.put("65", "交通通信");

        map.put("70", "芸術総記");
        map.put("71", "絵画彫刻");
        map.put("72", "写真工芸");
        map.put("73", "音楽舞踊");
        map.put("74", "演劇映画");
        map.put("75", "体育スポーツ");
        map.put("76", "諸芸娯楽");
        map.put("77", "家事");
        map.put("78", "生活");
        map.put("79", "コミック劇画");
        
        map.put("80", "語学総記");
        map.put("81", "日本語");
        map.put("82", "英米語");
        map.put("84", "ドイツ語");
        map.put("85", "フランス語");
        map.put("87", "各国語");
        
        map.put("90", "文学総記");
        map.put("91", "日本文学総記");
        map.put("92", "日本文学詩歌");
        map.put("93", "日本文学小説物語");
        map.put("95", "日本文学評論随筆その他");
        map.put("97", "外国文学小説");
        map.put("98", "外国文学その他");
        
        return map;
    }
    
    public static String convertCCodeToName(String cCode) {

        if (cCode == null || cCode.length() < 4) {
            return "不明";
        }

        // 1文字目取得
        int targetAudienceIndex = Integer.parseInt(cCode.substring(0, 1));
        
        // 2文字目取得
        int publicationFormatIndex = Integer.parseInt(cCode.substring(1, 2));
        
        String targetAudience = TARGET_AUDIENCES[targetAudienceIndex];
        String publicationFormat = PUBLICATION_FORMATS[publicationFormatIndex];

        String contentCode = cCode.substring(2, 4);
        String content = CONTENT_MAP.getOrDefault(contentCode, "未定義");
        
        String cName = targetAudience + "/" + publicationFormat + "/" + content;
        
        return cName;
    }
}
