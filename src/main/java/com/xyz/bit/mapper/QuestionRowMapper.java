package com.xyz.bit.mapper;

import com.xyz.bit.dto.QuestionDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


public class QuestionRowMapper implements RowMapper<QuestionDTO> {

  @Override
  public QuestionDTO mapRow(ResultSet rs, int arg1) throws SQLException {
    QuestionDTO ques = new QuestionDTO();
    ques.setQuestionId(rs.getInt("QUESTION_ID"));
    ques.setQuestionSerialNumber(rs.getInt("QUESTION_SERIAL_NO"));
    ques.setQuestion(rs.getString("QUESTION"));
    ques.setOptionOne(rs.getString("OPTION_ONE"));
    ques.setOptionOneValue(rs.getInt("OPTION_ONE_VALUE"));
    ques.setOptionTwo(rs.getString("OPTION_TWO"));
    ques.setOptionTwoValue(rs.getInt("OPTION_TWO_VALUE"));
    ques.setOptionThree(rs.getString("OPTION_THREE"));
    ques.setOptionThreeValue(rs.getInt("OPTION_THREE_VALUE"));
    ques.setOptionFour(rs.getString("OPTION_FOUR"));
    ques.setOptionFourValue(rs.getInt("OPTION_FOUR_VALUE"));
    //ques.setCorrectOption(rs.getInt("CORRECT_OPTION"));
    ques.setSubject(rs.getString("SUBJECT_NAME"));
    ques.setMarkedAnswer(0);
    return ques;
  }
}


