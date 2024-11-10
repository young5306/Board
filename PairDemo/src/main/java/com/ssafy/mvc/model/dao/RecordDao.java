package com.ssafy.mvc.model.dao;

import java.util.List;

public interface RecordDao {
	// 사용자 목록
	public List<Record> selectAll(String userId);
}
