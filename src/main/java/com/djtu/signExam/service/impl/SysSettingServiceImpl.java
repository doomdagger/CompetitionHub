package com.djtu.signExam.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djtu.signExam.dao.impl.SysSettingDao;
import com.djtu.signExam.dao.support.SQLWrapper;
import com.djtu.signExam.model.SysSetting;
import com.djtu.signExam.service.SysSettingService;

@Component("sysSettingService")
public class SysSettingServiceImpl implements SysSettingService {

	@Autowired
	private  SysSettingDao sysSettingDao;
	
	@Override
	public Object save(SysSetting sysSetting) {
		//if(sysSettingDao.add(sysSetting)){
			//return true;
		//}
		return sysSettingDao.add(sysSetting);
	}

	@Override
	public boolean saveByWrapper(SQLWrapper wrapper) {
		if(sysSettingDao.addByWrapper(wrapper) != null){
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(SysSetting sysSetting) {
		if(sysSettingDao.delete(sysSetting)){
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteByWrapper(SQLWrapper wrapper) {
		if(sysSettingDao.deleteByWrapper(wrapper)){
			return true;
		}
		return false;
	}

	@Override
	public boolean update(SysSetting sysSetting) {
		if(sysSettingDao.update(sysSetting)){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateByWrapper(SQLWrapper wrapper) {
		if(sysSettingDao.updateByWrapper(wrapper)){
			return true;
		}
		return false;
	}
	
	@Override
	public SysSetting selectById(Object...id) {
		return sysSettingDao.findOneById(id);
	}

	@Override
	public List<SysSetting> selectAll() {
		return sysSettingDao.findAll();
	}

	@Override
	public List<SysSetting> selectByWrapper(SQLWrapper wrapper) {
		return sysSettingDao.findAllByWrapper(wrapper);
	}

	@Override
	public List<Map<String, Object>> findByNativeQuery(String query) {
		return sysSettingDao.findByNativeQuery(query);
	}

}
