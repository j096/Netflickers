package com.community.netflickers.service.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Service
public class BaseTimeDto {
	
	protected String createdDate;
	protected String modifiedDate;
	
	public String toHhMmSsFormat(LocalDateTime dateTime){
		return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

}
