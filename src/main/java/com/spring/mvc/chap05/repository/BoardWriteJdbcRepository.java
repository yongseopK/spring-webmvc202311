package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("DBRepo")
@RequiredArgsConstructor
public class BoardWriteJdbcRepository implements BoardWriteRepository{
    private final JdbcTemplate template;

    @Override
    public List<Board> findAll() {
        String sql = "SELECT * FROM tbl_board ORDER BY board_no DESC";
        return template.query(sql, (rs, rn)-> new Board(rs));
    }

    @Override
    public Board findOne(int boardNo) {
        String selectSql = "SELECT * FROM tbl_board WHERE board_no = ?";
        Board board = template.queryForObject(selectSql, (rs, rn) -> new Board(rs), boardNo);

        if (board != null) {
            String updateSql = "UPDATE tbl_board SET view_count = view_count + 1 WHERE board_no = ?";
            template.update(updateSql, boardNo);
        }

        return board;
    }


    @Override
    public boolean save(Board board) {
        String sql = "INSERT INTO tbl_board (title, content) VALUE (?, ?)";
        return template.update(sql, board.getTitle(), board.getContent()) == 1;
    }

    @Override
    public boolean delete(int bno) {
        String sql = "DELETE FROM tbl_board WHERE board_no = ?";
        return template.update(sql, bno) == 1;
    }
}
