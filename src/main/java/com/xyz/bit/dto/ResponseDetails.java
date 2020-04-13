package com.xyz.bit.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Setter
@Getter
@ToString
@Service
public class ResponseDetails {

  private int id;
  private String details;
  private List<?> list;
}
