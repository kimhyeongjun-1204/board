package practice.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import practice.board.dto.BoardRequestsDto;
import practice.board.dto.BoardResponseDto;
import practice.board.dto.SuccessResponseDto;
import practice.board.service.BoardService;

import java.util.List;

@Controller
@RequiredArgsConstructor // final이나 @NonNull으로 선언된 필드만을 파라미터로 받는 생성자
public class BoardController {
    private final BoardService boardService;

    /* 1. 전체 게시글 조회 */
    @GetMapping("/")
    public String home(Model model) {
        List<BoardResponseDto> boards = boardService.getPosts();
        model.addAttribute("boards", boards);
        return "home";
    }

    /* 2. 게시글 작성 */
    @PostMapping("/api/post")
    public String createPost(@ModelAttribute("post") BoardRequestsDto requestsDto) {
        BoardResponseDto board = boardService.createPost(requestsDto);

        return "redirect:/api/post/" + board.getId();
    }

    /* 게시판 페이지 이동 */
    @GetMapping("/api/post")
    public String postPage(Model model) {
        model.addAttribute("post", new BoardRequestsDto());
        return "addForm";
    }

    /* 3. 특정 게시글 조회 */
    @GetMapping("/api/post/{id}")
    public String getPost(@PathVariable("id") Long id, Model model) {
        BoardResponseDto board = boardService.getPost(id);
        model.addAttribute("board", board);

        return "board";
    }

    /* 4. 게시글 수정 */
    @PutMapping("/api/post/{id}")
    @ResponseBody //컨트롤러가 JSON 데이터를 반환해야 할때
    public BoardResponseDto updatePost(@PathVariable Long id, @RequestBody BoardRequestsDto requestsDto) throws Exception {
        return boardService.updatePost(id, requestsDto);
    }

    /* 5. 게시글 삭제 */
    @DeleteMapping("/api/post/{id}")
    @ResponseBody
    public SuccessResponseDto deletePost(@PathVariable Long id, @RequestBody BoardRequestsDto requestDto) throws Exception {
        return boardService.deletePost(id, requestDto);
    }


    /* 6. 게시글 모두 삭제 */
    @DeleteMapping("/api/delete")
    @ResponseBody
    public SuccessResponseDto deleteAll() {
        return boardService.deleteAll();
    }

}
