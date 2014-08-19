package com.djtu.signExam.model.support.provider;

import java.util.List;
import java.util.Map;

import com.djtu.signExam.model.support.FieldEntry;
import com.djtu.signExam.model.support.FieldTypeMapper;
import com.djtu.signExam.model.support.TableDef;

public class MongoDBFieldTypeMapper implements FieldTypeMapper{

	
	@Override
	public Map<TableDef, List<FieldEntry>> fetchDatabaseMeta(String url,
			String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Class mapFieldType(FieldEntry entry) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
