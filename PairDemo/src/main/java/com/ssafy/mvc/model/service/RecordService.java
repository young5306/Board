package com.ssafy.mvc.model.service;

import java.util.List;


public interface RecordService {
	// 레코드 목록
	public List<Record> getRecordList(String userId);

}
