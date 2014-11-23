package com.djtu.signExam.action.compt.exportExcel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.djtu.signExam.action.compt.exportExcel.dto.TotalDto;
import com.djtu.signExam.model.TCompt;
import com.djtu.signExam.model.TSignin;
import com.djtu.signExam.model.support.ExcelBinderTool;
import com.djtu.signExam.service.compt.ComptManSignService;
import com.djtu.signExam.service.compt.ComptService;
import com.djtu.signExam.service.excel.ExcelService;


@Controller
@RequestMapping("/download/compt/sign")
public class GenExcel {
	
	@Autowired
	private ComptManSignService comptManSignService;
	@Autowired
	private ExcelService excelService;
	
	/*
	 * id 为赛事ID
	 */
	@RequestMapping(value = "/excel/{id}")
	public void DLForSignList(@PathVariable String id,HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException, Exception{
		//绑定TSignin这个实体
		ExcelBinderTool<TSignin> signBinder = new ExcelBinderTool<TSignin>(TSignin.class);
		signBinder.setSheetName("报名一览");
		signBinder.setTitle("Sign List of the competition *My Competition*");
		signBinder.bind("name", "名字")
					.bind("number", "学号")
					.bind("email", "邮箱")
					.bind("cellphone", "联系手机")
					.bind("creditCard", "银行卡号")
					.bind("creditCardBank", "所属银行")
					.bind("orderNum", "排序号")
					.bind("isPass", "是否通过")
					.bind("isReward", "是否获奖")
					.bind("isHelpCredit", "申请学分帮助")
					.bind("createtime", "申请时间")
					.bind("teacher", "指导老师")
					.bind("worksName", "作品名称");
		
		List<TSignin> rows = comptManSignService.getAllSignByComptId(id);
		
		
		//download
        OutputStream outs=response.getOutputStream();//获取文件输出IO流
        response.setContentType("application/x-download");//设置response内容的类型
        response.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode("参赛报名.xls", "UTF-8"));//设置头部信息
        
        signBinder.genExcel(rows, outs);
        outs.flush();
        //Runtime.getRuntime().exec("cmd  /c start "+savePath);
        outs.close();
	}
	
	
	@RequestMapping(value = "/total/{comptId}")
	public void getComptTotalExcel(HttpServletRequest request,HttpServletResponse response,@PathVariable String comptId) throws IOException{
		
		//get compt info
		TCompt compt = excelService.getComptInfoByComptId(comptId);
		
		//绑定TSignin这个实体
		ExcelBinderTool<TotalDto> signBinder = new ExcelBinderTool<TotalDto>(TotalDto.class);
		signBinder.setSheetName(compt.getTitle());
		signBinder.setTitle("赛事："+compt.getTitle());
		signBinder.bind("name", "名字")
					.bind("worksName", "作品名字")
					.bind("academy", "学院")
					.bind("profession", "专业")
					.bind("NO", "学号")
					.bind("cellphone", "联系手机")
					.bind("rewardLevel", "获奖等级")
					.bind("rewardTime", "获奖时间")
					.bind("leaderName", "队长名字")
					.bind("leaderCardNum", "队长卡号")
					.bind("teacher", "指导老师")
					.bind("publisher", "赛事发起方");
		//get all rows
		List<TotalDto> rows = excelService.getTotalDtoByComptId(comptId);
		String fileName = compt.getTitle()+"-总表"+".xls";
		
		//download
        OutputStream outs=response.getOutputStream();//获取文件输出IO流
        response.setContentType("application/x-download");//设置response内容的类型
        response.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));//设置头部信息
        try {
			signBinder.genExcel(rows, outs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
}
