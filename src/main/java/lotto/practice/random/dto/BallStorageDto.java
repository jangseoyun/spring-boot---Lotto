package lotto.practice.random.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BallStorageDto {

    /**
     * Admin 관리자 페이지가 될 것
     * 사용자가 추출한 모든 번호를 보관하는 저장소
     * - 사용자가 지난 회차 번호를 요청했을 때 검색할 수 있다
     */
    private Long UserNo;

    private Long cycleStorageNo;

    private Long storageCycle;//회차번호


}
