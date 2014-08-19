package com.djtu.signExam.service;

import java.util.List;
import java.util.Map;

import com.djtu.signExam.dao.support.SQLWrapper;
import com.djtu.signExam.model.SysSetting;

public interface SysSettingService {
	
	public Object save(SysSetting sysSetting);
	
	public boolean saveByWrapper(SQLWrapper wrapper);
	
	public boolean delete(SysSetting sysSetting);
	
	public boolean deleteByWrapper(SQLWrapper wrapper);
	
	public boolean update(SysSetting sysSetting);
	
	public boolean updateByWrapper(SQLWrapper wrapper);
	
	public SysSetting selectById(Object...id);
	
	public List<SysSetting> selectAll();
	
	public List<SysSetting> selectByWrapper(SQLWrapper wrapper);
	
	public List<Map<String, Object>> findByNativeQuery(String query);
}
