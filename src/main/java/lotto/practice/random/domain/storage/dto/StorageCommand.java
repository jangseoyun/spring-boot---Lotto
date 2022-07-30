package lotto.practice.random.domain.storage.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StorageCommand {

    private Long UserNo;

    private Long cycleStorageNo;

    private Long storageCycle;//회차번호
}
