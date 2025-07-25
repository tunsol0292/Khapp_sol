package com.kh.homework0724.board.mapper;

import com.kh.homework0724.board.vo.BoardVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Insert("""
            INSERT INTO BOARD(
                NO
                , TITLE
                , CONTENT
                , CATEGORY_NO
                , CREATE_AT
                , MODIFIED_AT
            ) VALUES (
                SEQ_BOARD.NEXTVAL
                , #{title}
                , #{content}
                , #{categoryNo}
                , SYSDATE
                , SYSDATE
            )
            """)
    int insert(BoardVo boardVo);

    @Select("""
            SELECT
                B.NO
                , B.TITLE
                , B.CONTENT
                , C.CATEGORY_NAME
            FROM BOARD B
            INNER JOIN CATEGORY C
            ON B.CATEGORY_NO = C.CATEGORY_NO
            WHERE DEL_YN = 'N'
            """)
    List<BoardVo> boardList();

    @Select("""
            SELECT
                B.NO
                , B.TITLE
                , B.CONTENT
                , C.CATEGORY_NAME
                , B.CREATE_AT
                , B.MODIFIED_AT
            FROM BOARD B
            INNER JOIN CATEGORY C
            ON B.CATEGORY_NO = C.CATEGORY_NO
            WHERE DEL_YN = 'N'
            AND NO = #{no}
            """)
    BoardVo boardOneByNo(int no);

    @Delete("""
            UPDATE BOARD
            SET
                DEL_YN = 'Y'
            WHERE NO = #{no}
            """)
    int delete(int no);

    @Update("""
            UPDATE BOARD
            SET
                TITLE = #{title}
                , CONTENT = #{content}
                , MODIFIED_AT = SYSDATE
            WHERE NO = #{no}
            AND DEL_YN = 'N'
            """)
    int update(BoardVo boardVo);
}