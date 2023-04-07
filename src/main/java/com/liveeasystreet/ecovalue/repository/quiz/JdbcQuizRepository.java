package com.liveeasystreet.ecovalue.repository.quiz;

import com.liveeasystreet.ecovalue.domain.Quiz;
import com.liveeasystreet.ecovalue.dto.QuizDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Primary
@Repository
@Slf4j
public class JdbcQuizRepository implements QuizRepository{

    private final DataSource dataSource;

    public JdbcQuizRepository(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    @Override
    public void save(Quiz quiz) {
        String sql = "insert into quiz values(?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,quiz.getCategory());
            pstmt.setString(2,quiz.getTitle());
            pstmt.setString(3,quiz.getDetail());
            pstmt.setString(4,quiz.getSolve());
            pstmt.setBoolean(5,quiz.getAnswer());
            pstmt.setInt(6,quiz.getOccurredProblemCount());
            pstmt.setInt(7,quiz.getNumberOfHits());
            pstmt.setDate(8, java.sql.Date.valueOf( quiz.getDate()));

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                quiz.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
    {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }

    @Override
    public List<Quiz> findAll(QuizSearchCond cond) {
        String sql = "select * from quiz";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Quiz> quizs = new ArrayList<>();
            while(rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(rs.getLong("quiz_id"));
                quiz.setCategory(rs.getString("quiz_category"));
                quiz.setTitle(rs.getString("quiz_title"));
                quiz.setDetail(rs.getString("quiz_detail"));
                quiz.setSolve(rs.getString("quiz_solve"));
                quiz.setAnswer(rs.getBoolean("quiz_answer"));
                quiz.setOccurredProblemCount(rs.getInt("quiz_occurredProblemCount"));
                quiz.setNumberOfHits(rs.getInt("quiz_numberOfHits"));
                quiz.setDate(rs.getDate("quiz_date").toLocalDate());

                quizs.add(quiz);
            }
            return quizs;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Long> findAllkeyList() {
        return null;
    }

    @Override
    public Optional<Quiz> findById(Long id) {
        String sql = "select * from quiz where quiz_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(rs.getLong("quiz_id"));
                quiz.setCategory(rs.getString("quiz_category"));
                quiz.setTitle(rs.getString("quiz_title"));
                quiz.setDetail(rs.getString("quiz_detail"));
                quiz.setSolve(rs.getString("quiz_solve"));
                quiz.setAnswer(rs.getBoolean("quiz_answer"));
                quiz.setOccurredProblemCount(rs.getInt("quiz_occurredProblemCount"));
                quiz.setNumberOfHits(rs.getInt("quiz_numberOfHits"));
                quiz.setDate(rs.getDate("quiz_date").toLocalDate());
                return Optional.of(quiz);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public void update(Long id, QuizDTO updateParam) {

    }

    @Override
    public void update(Quiz quiz) {

    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM quiz WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,id);
            int res = pstmt.executeUpdate();

            if (res>0) {
                log.info("삭제 성공");
            } else {
                throw new SQLException("삭제 실패");
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
}
