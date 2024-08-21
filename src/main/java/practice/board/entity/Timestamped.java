package practice.board.entity;


import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass // Entity 클래스가 상속할 때 필드를 칼럼으로 동일하게 인식할 수 있도록함
@EntityListeners(AuditingEntityListener.class)
public abstract class Timestamped {

    // 작성일시
    @CreatedDate
    private String createdAt;

    // 수정일시
    @LastModifiedDate
    private String modifiedAt;

    @PrePersist
    public void onPrePersist(){
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd HH:mm"));
        this.modifiedAt = this.createdAt;
    } 
    
    @PreUpdate
    public void onPreUpdate(){
        this.modifiedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd HH:mm"));
    }

}
