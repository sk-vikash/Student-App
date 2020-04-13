package com.xyz.bit.mapper;

import com.xyz.bit.dto.VerifyAnswerDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class VerifyAnswerRowMapper implements RowMapper<VerifyAnswerDTO> {

  @Override
  public VerifyAnswerDTO mapRow(ResultSet rs, int arg1) throws SQLException {
    VerifyAnswerDTO verifyAnswerDTO = new VerifyAnswerDTO();
    verifyAnswerDTO.setQuestionId(rs.getInt("QUESTION_ID"));
    verifyAnswerDTO.setCorrectOption(rs.getInt("CORRECT_OPTION"));
    return verifyAnswerDTO;
  }
}