package practice.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardRequestsDto {
    private String title;
    private String content;
    private String author;
    private String password;
}


