package practice.board.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import practice.board.dto.BoardRequestsDto;
import practice.board.dto.BoardResponseDto;
import practice.board.dto.SuccessResponseDto;
import practice.board.service.BoardService;

import java.util.List;

@RestController
@RequiredArgsConstructor // final이나 @NonNull으로 선언된 필드만을 파라미터로 받는 생성자
public class BoardController {
    private final BoardService boardService;


    /* 1. 전체 게시글 조회 */
    @GetMapping("/")
    public String home(Model model) {
        List<BoardResponseDto> posts = boardService.getPosts();
        model.addAttribute("posts", posts);
        return "/home";
    }


    /* 2. 게시글 작성 */
    @PostMapping("/api/post")
    public BoardResponseDto createPost(@RequestBody BoardRequestsDto requestsDto) {
        return boardService.createPost(requestsDto); 
    }

    /* 3. 특정 게시글 조회 */
    @GetMapping("/api/post/{id}")
    public BoardResponseDto getPost(@PathVariable Long id) {
        return boardService.getPost(id);
    }

    /* 4. 게시글 수정 */
    @PutMapping("/api/post/{id}")
    public BoardResponseDto updatePost(@PathVariable Long id, @RequestBody BoardRequestsDto requestsDto) throws Exception {
        return boardService.updatePost(id, requestsDto);
    }

    /* 5. 게시글 삭제 */
    @DeleteMapping("/api/post/{id}")
    public SuccessResponseDto deletePost(@PathVariable Long id, @RequestBody BoardRequestsDto requestDto) throws Exception {
        return boardService.deletePost(id, requestDto);
    }
}
