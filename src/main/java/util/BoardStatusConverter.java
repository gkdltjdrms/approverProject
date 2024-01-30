package util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BoardStatusConverter {

    private static final Map<String, String> koreanStatusMap;

    static {
        koreanStatusMap = new HashMap<>();
        koreanStatusMap.put("save", "임시 저장");
        koreanStatusMap.put("wait", "결재 대기");
        koreanStatusMap.put("checking", "결재중");
        koreanStatusMap.put("end", "결재 완료");
        koreanStatusMap.put("reject", "반려");
    }

    public static String getKoreanStatus(String flag) {
        return Optional.ofNullable(koreanStatusMap.get(flag)).orElse(flag);
    }
}
