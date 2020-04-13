package com.xyz.bit.mapper;

import com.xyz.bit.dto.ResultDTO;
import com.xyz.bit.utility.CommUtility;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ResultDataRowMapper implements RowMapper<ResultDTO> {

  @Override
  public ResultDTO mapRow(ResultSet rs, int arg1) throws SQLException {
    ResultDTO dto = new ResultDTO();
    dto.setResultId(rs.getInt("RESULT_ID"));
    dto.setStudentId(rs.getInt("STUDENT_ID"));
    dto.setTestId(rs.getInt("TEST_ID"));
    dto.setRegistered(rs.getString("REGISTERED"));
    dto.setTestTaken(rs.getString("TEST_TAKEN"));
    dto.setTestTakenDate(rs.getDate("TEST_TAKEN_DATE"));
    dto.setTestQuestion(rs.getInt("TOTAL_QUESTION"));
    dto.setCorrectAnswer(rs.getInt("CORRECT_ANSWER"));
    dto.setInCorrectAnswer(rs.getInt("INCORRECT_ANSWER"));
    dto.setFormattedTestTakenDate(CommUtility.formatDateToString(dto.getTestTakenDate()));
    return dto;
  }
}