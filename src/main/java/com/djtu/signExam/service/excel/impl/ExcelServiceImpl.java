package com.djtu.signExam.service.excel.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djtu.signExam.action.compt.exportExcel.dto.SignDto;
import com.djtu.signExam.action.compt.exportExcel.dto.TotalDto;
import com.djtu.signExam.dao.impl.TSigninDao;
import com.djtu.signExam.dao.support.IOperators.SORT;
import com.djtu.signExam.dao.support.SQLWrapper;
import com.djtu.signExam.dao.support.Sortable;
import com.djtu.signExam.model.TCompt;
import com.djtu.signExam.model.TSignin;
import com.djtu.signExam.service.compt.ComptService;
import com.djtu.signExam.service.compt.ComptSigninService;
import com.djtu.signExam.service.excel.ExcelService;


@Component("excelService")
public class ExcelServiceImpl implements ExcelService{
	
	@Autowired
	private ComptService comptService;
	@Autowired
	private ComptSigninService signinService;
	
	@Autowired
	private TSigninDao signDao;

	@Override
	public List<SignDto> getSignDtoByComptId(String comptId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TotalDto> getTotalDtoByComptId(String comptId) {
		
		List<TSignin> signList = signDao.findAllByWrapper(SQLWrapper.instance().selectAll().where()
					.eq("sk_t_compt", comptId).eq("isPass", 1)
					.orderBy(Sortable.inSort("isReward", SORT.DESC))
					.orderBy(Sortable.inSort("teamNo", SORT.ASC))//这步很重要
					.orderBy(Sortable.inSort("isLeader", SORT.DESC))//这步很重要
					.orderBy(Sortable.inSort("isPass", SORT.DESC))
					.orderBy(Sortable.inSort("name", SORT.ASC)));
		
		List<TotalDto> resultList = new ArrayList<TotalDto>();
		String currentTeamNo = "";
		String currentTeamLeader = "";
		boolean isSameTeam = false;
		for(int i = 0,size = signList.size(); i < size; i++){
			
			isSameTeam = currentTeamNo!=signList.get(i).getTeamNo()?false:true;
			currentTeamNo = currentTeamNo!=signList.get(i).getTeamNo()?signList.get(i).getTeamNo():currentTeamNo;
			currentTeamLeader = (currentTeamNo!=signList.get(i).getTeamNo())&&(signList.get(i).getIsLeader())?
					signList.get(i).getName():currentTeamLeader;
			
			TotalDto dto = new TotalDto();
			dto.setName(signList.get(i).getName());//name
			dto.setWorksName("");
			dto.setAcademy(signList.get(i).getAcademy());//academy
			dto.setProfession(signList.get(i).getProfession());//profession
			dto.setNO(signList.get(i).getNumber());//NO
			dto.setCellphone(signList.get(i).getCellphone());//cellphone
			dto.setRewardLevel(signList.get(i).getReward());//rewardLevel
			dto.setRewardTime(signList.get(i).getRewardDate().toString());//rewardTime
			dto.setLeaderName(currentTeamLeader);//leader
			dto.setLeaderCardNum(!isSameTeam&&signList.get(i).getIsLeader()?signList.get(i).getCreditCard():"");//leaderCardNum
			dto.setTeacher(signList.get(i).getTeacher());//teacher
			dto.setPublisher(signList.get(i).getComptAdminer());//adminer
			
			
			//add to list
			resultList.add(dto);
		}
		return resultList;
	}

	@Override
	public TCompt getComptInfoByComptId(String comptId) {
		return comptService.getComptById(comptId);
	}
	
	

}
