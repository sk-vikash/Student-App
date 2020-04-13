package com.xyz.bit.mapper;

import com.xyz.bit.dto.NotificationDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class NotificationRowMapper implements RowMapper<NotificationDTO> {

  @Override
  public NotificationDTO mapRow(ResultSet rs, int arg1) throws SQLException {
    NotificationDTO notificationDTO = new NotificationDTO();
    notificationDTO.setMessage(rs.getString("MESSAGE"));
    notificationDTO.setStartDate(rs.getString("START_DATE"));
    notificationDTO.setEndDate(rs.getString("END_DATE"));
    notificationDTO.setPriority(rs.getString("PRIORITY"));
    return notificationDTO;
  }
}