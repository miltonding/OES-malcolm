package com.malcolm.oes.dao.jdbc.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.malcolm.oes.dao.QuestionDao;
import com.malcolm.oes.model.Pagination;
import com.malcolm.oes.model.Question;
import com.malcolm.oes.model.User;
import com.malcolm.oes.util.StringUtil;

public class QuestionDaoImpl implements QuestionDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveQuestion(final Question question, final User user) {
        if (question == null) {
            return 0;
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                String sql = "INSERT INTO question(question_name, user_id, answer_a, answer_b, answer_c, answer_d, question_answer, is_deleted, question_id, created_time, edited_time)"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, question.getQuestionTitle());
                stmt.setInt(2, user.getId());
                stmt.setString(3, question.getAnswerA());
                stmt.setString(4, question.getAnswerB());
                stmt.setString(5, question.getAnswerC());
                stmt.setString(6, question.getAnswerD());
                stmt.setString(7, question.getAnswer());
                stmt.setInt(8, 0);
                stmt.setString(9, question.getQuestionId());
                return stmt;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public List<Question> findQuestionList(String currentOrder, final Pagination pagination) {
        if (pagination.getNowPage() > pagination.getPageCount()) {
            pagination.setNowPage(pagination.getPageCount());
        }
        String sql = "SELECT id, question_id, question_name FROM question WHERE is_deleted = 0 ORDER BY question_id "
                + currentOrder + " LIMIT ?, ?";

        Object args[] = { pagination.getStartRecord(), pagination.getPageSize() };

        List<Question> questionList = jdbcTemplate.query(sql, args, new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestionId(rs.getString("question_id"));
                question.setQuestionTitle(rs.getString("question_name"));
                return question;
            }
        });
        return questionList;
    }

    public List<Question> findQuestioinListByKeyword(final String keyword, String currentOrder,
            final Pagination pagination) {
        if (pagination.getNowPage() > pagination.getPageCount()) {
            pagination.setNowPage(pagination.getPageCount());
        }
        String sql = "SELECT id, question_id, question_name FROM question "
                + "WHERE is_deleted = 0 AND (question_id LIKE ? OR question_name LIKE ?) ORDER BY question_id "
                + currentOrder + " LIMIT ?, ?";

        List<Question> questionList = jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, "%" + keyword + "%");
                stmt.setString(2, "%" + keyword + "%");
                stmt.setInt(3, pagination.getStartRecord());
                stmt.setInt(4, pagination.getPageSize());
            }
        }, new RowMapper<Question>() {
            public Question mapRow(ResultSet rs, int arg1) throws SQLException {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestionId(rs.getString("question_id"));
                question.setQuestionTitle(rs.getString("question_name"));
                return question;
            }
        });
        return questionList;
    }

    public int getQuestionRecordCount() {
        String sql = "SELECT COUNT(*) FROM question WHERE is_deleted = 0";
        Number number = jdbcTemplate.queryForObject(sql, Number.class);
        return number == null ? 0 : number.intValue();
    }

    public int getQuestionRecordCountByKeyword(final String keyword) {
        String sql = "SELECT COUNT(*) FROM question WHERE is_deleted = 0 AND (question_id LIKE ? OR question_name LIKE ?)";
        Object args[] = { "%" + keyword + "%", "%" + keyword + "%" };
        Number number = jdbcTemplate.queryForObject(sql, args, Number.class);
        return number == null ? 0 : number.intValue();
    }

    // This method update the question's status used by ids [].
    public int updateQuestionStatusByIds(String ids) {
        String sql = "UPDATE question SET is_deleted = 1, edited_time = NOW() WHERE id IN (" + ids + ")";
        return jdbcTemplate.update(sql);
    }

    public Question getQuestionById(int id) {
        String sql = "SELECT id, question_id, question_name, answer_a, answer_b, answer_c, answer_d, question_answer FROM question WHERE id = "
                + id;
        List<Question> questionList = jdbcTemplate.query(sql, new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestionId(rs.getString("question_id"));
                question.setQuestionTitle(rs.getString("question_name"));
                question.setAnswer(rs.getString("question_answer"));
                question.setAnswerA(rs.getString("answer_a"));
                question.setAnswerB(rs.getString("answer_b"));
                question.setAnswerC(rs.getString("answer_c"));
                question.setAnswerD(rs.getString("answer_d"));
                return question;
            }
        });
        if (questionList != null && questionList.size() > 0) {
            return questionList.get(0);
        }
        return null;
    }

    public Question getQuestionByName(String name) {
        String sql = "SELECT * FROM question WHERE question_id = ? AND is_deleted = 0";
        Object args[] = { name };
        List<Question> questionList = jdbcTemplate.query(sql, args, new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestionId(rs.getString("question_id"));
                question.setQuestionTitle(rs.getString("question_name"));
                question.setAnswer(rs.getString("question_answer"));
                question.setAnswerA(rs.getString("answer_a"));
                question.setAnswerB(rs.getString("answer_b"));
                question.setAnswerC(rs.getString("answer_c"));
                question.setAnswerD(rs.getString("answer_d"));
                return question;
            }
        });
        if (questionList != null && questionList.size() > 0) {
            return questionList.get(0);
        }
        return null;
    }

    @Override
    public List<Integer> getRandomQuestions(int questionQuantity) {
        String sql = "SELECT id FROM question WHERE is_deleted = 0 ORDER BY RAND() LIMIT #{value}";
        List<Integer> questionIds = jdbcTemplate.query(sql,new RowMapper<Integer>(){

            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("id");
        }});
        return questionIds;
    }

    @Override
    public int updateUsedQuestions(List<Integer> questionIds, int usedStatus) {
        String ids = StringUtil.revertListToString(questionIds);
        String sql = "UPDATE question SET is_used = #{usedStatus} WHERE id IN (" + ids + ")";
        return jdbcTemplate.update(sql);
    }

    @Override
    public int getAllQuestionCount() {
        return 0;
    }

}
