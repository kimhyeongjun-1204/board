package practice.board.entity;


import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // Entity 클래스가 이를 상속할 수 있게함
@EntityListeners(AuditingEntityListener.class)
public class Timestamped {

    // 작성일시
    @CreatedDate
    private LocalDateTime createdAt;

    // 수정일시
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
