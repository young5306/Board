package com.ssafy.mvc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mvc.model.dao.RecordDao;

@Service
public class RecordServiceImpl implements RecordService {
	
	private final RecordDao recordDao;
	
	@Autowired
	public RecordServiceImpl(RecordDao recordDao) {
		this.recordDao = recordDao;
	}
	
	@Override
	public List<Record> getRecordList(String userId) {
		return recordDao.selectAll(userId);
	}
}
