package util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BoardStatusConverter {

    private static final Map<String, String> koreanStatusMap;

    static {
        koreanStatusMap = new HashMap<>();
        koreanStatusMap.put("save", "�ӽ� ����");
        koreanStatusMap.put("wait", "���� ���");
        koreanStatusMap.put("checking", "������");
        koreanStatusMap.put("end", "���� �Ϸ�");
        koreanStatusMap.put("reject", "�ݷ�");
    }

    public static String getKoreanStatus(String flag) {
        return Optional.ofNullable(koreanStatusMap.get(flag)).orElse(flag);
    }
}
