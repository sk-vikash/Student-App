package com.xyz.bit.mapper;

import com.xyz.bit.dto.TestDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class TestRowMapper implements RowMapper<TestDTO> {

  @Override
  public TestDTO mapRow(ResultSet rs, int arg1) throws SQLException {
    TestDTO dto = new TestDTO();
    dto.setTestId((rs.getInt("TEST_ID")));
    dto.setTestReferenceNumber(rs.getString("TEST_REFERENCE_NUMBER"));
    dto.setCourseId(rs.getInt("COURSE_ID"));
    dto.setStudentId(rs.getInt("STUDENT_ID"));
    //dto.setTestTaken(rs.getString("TEST_TAKEN"));
    //dto.setTestName(rs.getString("TEST_NAME"));
    return dto;
  }
}
