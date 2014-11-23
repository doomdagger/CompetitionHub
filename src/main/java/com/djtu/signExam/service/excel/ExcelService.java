package com.djtu.signExam.service.excel;

import java.util.List;

import com.djtu.signExam.action.compt.exportExcel.dto.SignDto;
import com.djtu.signExam.action.compt.exportExcel.dto.TotalDto;
import com.djtu.signExam.model.TCompt;

public interface ExcelService {
	
	public TCompt getComptInfoByComptId(String comptId);
	
	public List<SignDto> getSignDtoByComptId(String comptId);
	
	public List<TotalDto> getTotalDtoByComptId(String comptId);

}
