package com.malcolm.oes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.malcolm.oes.common.JDBCAbstractCallback;
import com.malcolm.oes.common.JdbcTemplate;
import com.malcolm.oes.model.PageBean;
import com.malcolm.oes.model.Question;
import com.malcolm.oes.model.User;
import com.malcolm.oes.util.StringUtil;

public class QuestionDao {
    JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<Question>();

    public int saveQuestion(final Question question, final User user) {
        if (question == null) {
            return 0;
        }
        String sql = "INSERT INTO question(question_name, user_id, answer_a, answer_b, answer_c, answer_d, question_answer, question_status, question_id, created_time, edited_time)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, now(), now())";
        int id = jdbcTemplate.insert(sql, new JDBCAbstractCallback<Question>() {
            public void setParams(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, question.getQuestionTitle());
                stmt.setInt(2, user.getId());
                stmt.setString(3, question.getAnswerA());
                stmt.setString(4, question.getAnswerB());
                stmt.setString(5, question.getAnswerC());
                stmt.setString(6, question.getAnswerD());
                stmt.setString(7, question.getAnswer());
                stmt.setInt(8, question.getStatus());
                stmt.setString(9, question.getQuestionId());
            }
        });
        return id;
    }

    public List<Question> findQuestionList(final PageBean pageBean) {

        List<Question> questionList = new ArrayList<Question>();
        if (pageBean.getNowPage() > pageBean.getPageCount()) {
            pageBean.setNowPage(pageBean.getPageCount());
        }
        String sql = "SELECT id, question_id, question_name FROM question WHERE question_status = 1 order by edited_time desc limit ?, ?";
        questionList = jdbcTemplate.query(sql, new JDBCAbstractCallback<Question>() {
            public void setParams(PreparedStatement stmt) throws SQLException {
                stmt.setInt(1, pageBean.getStartRecord());
                stmt.setInt(2, pageBean.getPageSize());
            }

            public Question rsToObject(ResultSet rs) throws SQLException {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestionId(rs.getString("question_id"));
                question.setQuestionTitle(rs.getString("question_name"));
                return question;
            }
        });
        return questionList;
    }

    public List<Question> findQuestioinListByKeyword(final String keyword, final PageBean pageBean) {
        List<Question> questionList = new ArrayList<Question>();
        if (pageBean.getNowPage() > pageBean.getPageCount()) {
            pageBean.setNowPage(pageBean.getPageCount());
        }
        String sql = "SELECT id, question_id, question_name FROM question "
                + "where question_status = 1 and question_id like ? or question_name like ? order by edited_time desc limit ?, ?";
        questionList = jdbcTemplate.query(sql, new JDBCAbstractCallback<Question>() {
            public void setParams(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, "%" + keyword + "%");
                stmt.setString(2, "%" + keyword + "%");
                stmt.setInt(3, pageBean.getStartRecord());
                stmt.setInt(4, pageBean.getPageSize());
            }

            public Question rsToObject(ResultSet rs) throws SQLException {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestionId(rs.getString("question_id"));
                question.setQuestionTitle(rs.getString("question_name"));
                return question;
            }
        });

        return questionList;
    }

    public int getquestionRecordCount() {
        String sql = "SELECT COUNT(*) FROM question where question_status = 1";
        int recordCount = 0;
        recordCount = jdbcTemplate.getCount(sql);
        return recordCount;
    }

    public int getquestionRecordCount(final String keyword) {
        String sql = "SELECT COUNT(*) FROM question where question_status = 1 and question_id like ? or question_name like ?";
        int recordCount = 0;
        recordCount = jdbcTemplate.getCount(sql, new JDBCAbstractCallback<Question>() {
            public void setParams(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, "%" + keyword + "%");
                stmt.setString(2, "%" + keyword + "%");
            }
        });
        return recordCount;
    }

    public int deleteQuestionByIds(final int ids[]) {
        String key = StringUtil.revertArrayToString(ids);
        String sql = "DELETE FROM question where id in " + key;
        int count = 0;
        count = jdbcTemplate.updateOrDeleteByIds(sql, new JDBCAbstractCallback<Question>() {
        });
        return count;
    }

    public int updateQuestionStatusByIds(int ids[]) {
        String key = StringUtil.revertArrayToString(ids);
        String sql = "UPDATE question SET question_status = 0, edited_time = now() where id in " + key;
        int count = 0;
        count = jdbcTemplate.updateOrDeleteByIds(sql, new JDBCAbstractCallback<Question>() {
        });
        return count;
    }

    public Question getQuestionById(int id) {
        String sql = "SELECT id, question_id, question_name, answer_a, answer_b, answer_c, answer_d, question_answer FROM question WHERE id = "
                + id;
        Question question = jdbcTemplate.queryOne(sql, new JDBCAbstractCallback<Question>() {
            public Question rsToObject(ResultSet rs) throws SQLException {
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
        return question;
    }

    public int updateQuestionStatusById(final int id) {
        String sql = "UPDATE question SET question_status = 0, edited_time = now() where id = ?";
        int count = 0;
        count = jdbcTemplate.updateOrDeleteByIds(sql, new JDBCAbstractCallback<Question>() {
            public void setParams(PreparedStatement stmt) throws SQLException {
                stmt.setInt(1, id);
            }
        });
        return count;
    }
}
