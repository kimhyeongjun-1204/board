package practice.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.board.dto.BoardRequestsDto;
import practice.board.dto.BoardResponseDto;
import practice.board.dto.SuccessResponseDto;
import practice.board.entity.Board;
import practice.board.repository.BoardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional( readOnly = true) // 조회용 메소드 표시
    public List<BoardResponseDto> getPosts() {
        // toList : dto -> List로 변환
        return boardRepository.findAllByOrderByModifiedAtAsc().stream().map(BoardResponseDto::new).toList();
    }

    // 요청이 저장된 board 객체 다시 리턴
    @Transactional
    public BoardResponseDto createPost(BoardRequestsDto requestsDto) {
        Board board = new Board(requestsDto);
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    // 특정 게시글 조회
    @Transactional
    public BoardResponseDto getPost(Long id) {
        return boardRepository.findById(id).map(BoardResponseDto::new).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
    }

    @Transactional
    public BoardResponseDto updatePost(Long id, BoardRequestsDto requestsDto) throws Exception {
        /* DB에서 해당 게시판 정보 가져옴 */
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        if(!requestsDto.getPassword().equals(board.getPassword())) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        /* Dirty Checking : 조회한 Entity 값을 변경하면 DB도 같이 변경됨  */
        board.update(requestsDto);
        return new BoardResponseDto(board);
    }

    @Transactional
    public SuccessResponseDto deletePost(Long id, BoardRequestsDto requestsDto) throws Exception {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        if(!requestsDto.getPassword().equals(board.getPassword()))
            throw new Exception("비밀번호가 일치하지 않습니다.");

        boardRepository.deleteById(id);
        return new SuccessResponseDto(true);
    }

    /* 게시물 전체 삭제 */
    @Transactional
    public SuccessResponseDto deleteAll() {
        boardRepository.deleteAll();
        return new SuccessResponseDto(true);
    }
}
