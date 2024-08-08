package practice.board.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practice.board.dto.BoardRequestsDto;

@Getter  // Lombok 제공 : 객체의 getter 코드 자동 완성
@Setter
@Entity // DB에 들어갈 엔티티임을 표시
@NoArgsConstructor
public class Board extends Timestamped {

    public Board(BoardRequestsDto requestsDto) {
        this.title = requestsDto.getTitle();
        this.contents = requestsDto.getContent();
        this.author = requestsDto.getAuthor();
        this.password = requestsDto.getPassword();
    }

    // 기본키를 자동으로 생성해줌
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String password;

    //생성일시, 수정일시는 상속받아 사용함

    public void update(BoardRequestsDto requestsDto) {
        this.title = requestsDto.getTitle();
        this.contents = requestsDto.getContent();
    }

}
