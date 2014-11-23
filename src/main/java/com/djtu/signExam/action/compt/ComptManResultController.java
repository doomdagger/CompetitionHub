package com.djtu.signExam.action.compt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.djtu.signExam.model.TCompt;
import com.djtu.signExam.model.TComptAttachment;
import com.djtu.signExam.model.TResultProf;
import com.djtu.signExam.model.TSignin;
import com.djtu.signExam.service.compt.ComptAttchmentService;
import com.djtu.signExam.service.compt.ComptManSignService;
import com.djtu.signExam.service.compt.ComptService;
import com.djtu.signExam.service.compt.ProfService;
import com.djtu.signExam.util.StringConst;
import com.djtu.signExam.util.UploadConst;


@Controller
@RequestMapping("/man/compt/sign/result")
public class ComptManResultController {
	
	
	@Autowired
	private ComptManSignService comptManSignService;
	@Autowired
	private ComptService comptService;
	@Autowired
	private ComptAttchmentService attchService;
	@Autowired
	private ProfService profService;
	
	//const for page nav
    private final String NAV_BAR = "navbar";
    private final String CURR_MAN_BAR = "comptMan";
    
	/*
	 * 确认赛事结果
	 */
	@RequestMapping(value = "/confirmResult")
	public String confirmResult(@RequestParam String comptLink,Model model){
		//get the compt
		TCompt compt = comptService.getComptById(comptLink);
		//get the prof
		
		model.addAttribute("compt", compt);
		model.addAttribute("comptLink",comptLink);
		model.addAttribute(NAV_BAR, CURR_MAN_BAR);
		return "comptManConfirmResult";
	}
	
	/**
	 * 确认获得成绩的队伍/个人,同时保存上传的获奖证明
	 * @throws IOException 
	 */
	@RequestMapping(value = "/confirmResultTeam",method = RequestMethod.POST)
	public String confirmResultTeam(@RequestParam String comptLink,String type,
									@RequestParam String remark,@RequestParam MultipartFile upFile,
									Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		
		String savePath = request.getServletContext().getRealPath(UploadConst.WORKS_RES_PATH);
        
        if (!new File(savePath).isDirectory())
        {
        	new File(savePath).mkdirs();
        }
        //文件名
        String name = "";
        //保存路径
        String urlPath = "";
        
        //获取文件输出流
        System.out.println("文件大小："+upFile.getSize());
        
        if(upFile.getSize() > UploadConst.COMMON_MAX_FILESIZE){
        	response.setHeader("text/html","UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("上传文件大小超出10M");
            return null;
        }
        
        
    	try {
        	//获取上传文件旧名
            name = upFile.getOriginalFilename();
            //获取后缀名
            //String suffix = name.substring(name.lastIndexOf(".")+1);
            //创建上传目标文件
            //String newFileName = "COVER"+System.currentTimeMillis() + new Random(50000).nextInt() + "."+suffix;
            File targetFile = new File(savePath,name);
            //获得保存数据库的http访问路径
            urlPath = UploadConst.WORKS_URL_PATH.replaceAll("\\\\", "/")+name;
            //创建输入输出流
        	InputStream is = upFile.getInputStream();
        	FileOutputStream fos = new FileOutputStream(targetFile);
        	FileCopyUtils.copy(is, fos);
        	
        	//上传成功 - 添加佐证材料
    		TResultProf prof = new TResultProf();
    		prof.setCreatetime(new Date());
    		prof.setRemark(remark);
    		prof.setFilepath(urlPath);
    		prof.setComptId(comptLink);
    		profService.addOne(prof);
        	
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("上传文件失败");
			response.setHeader("text/html","UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("上传文件失败");
		}
    	
    	
		
		//根据comptId设置compt的获奖状态
		if("reward".equals(type)){
			comptManSignService.confirmReward(comptLink,true);
		}
		
		/*List<TSignin> signList = comptManSignService.getAllPassTeamByComptId(comptLink);
		TCompt compt = comptService.getComptById(comptLink);
		model.addAttribute("compt", compt);
		model.addAttribute("signList",signList);
		model.addAttribute("comptLink",comptLink);
		model.addAttribute(NAV_BAR, CURR_MAN_BAR);*/
		return "redirect:/man/compt/sign/result/addResultTeam?comptLink="+comptLink;
	}
	
	//进入录入成绩页面
	@RequestMapping(value = "/addResultTeam")
	public String addResultTeam(@RequestParam String comptLink,Model model){
		List<TSignin> signList = comptManSignService.getAllPassTeamByComptId(comptLink);
		TCompt compt = comptService.getComptById(comptLink);
		model.addAttribute("compt", compt);
		model.addAttribute("signList",signList);
		model.addAttribute("comptLink",comptLink);
		model.addAttribute(NAV_BAR, CURR_MAN_BAR);
		return "comptManConfirmResultTeam";
	}
	
	/*
	 * 赛事没有获奖，结束该赛事
	 */
	@RequestMapping(value = "/confirmNoReward")
	public String confirmNoReward(@RequestParam String comptLink){
		comptManSignService.confirmReward(comptLink,false);
		return "redirect:/man/compt/list";
	}
	
	/*
	 * 添加获奖队伍
	 */
	@RequestMapping(value = "/addReward")
	public @ResponseBody String addReward(@RequestParam String teamNo,@RequestParam String result){
		return comptManSignService.addReward(teamNo, result)?StringConst.AJAX_SUCCESS:StringConst.AJAX_FAIL;
	}
	
	/*
	 * 确认完成所有的录入
	 */
	@RequestMapping(value = "/confirmFinish")
	public @ResponseBody String confirmFinish(@RequestParam String comptLink){
		return comptManSignService.confirmFinish(comptLink, 6)?StringConst.AJAX_SUCCESS:StringConst.AJAX_FAIL;
	}
	
	/*
	 * 查看赛事结果
	 */
	@RequestMapping(value = "/checkResult")
	public String checkResult(@RequestParam String comptLink,Model model){
		
		//get the compt
		TCompt compt = comptService.getComptById(comptLink);
		//get the prof
		TResultProf prof = profService.getOneByComptId(comptLink);
		
		List<TSignin> rewardList = null;
		boolean isReward = compt.getIsReward();
		if(compt.getIsReward()){
			rewardList = comptManSignService.getAllRewardTeam(comptLink);
		}
		
		model.addAttribute("prof", prof);
		model.addAttribute("compt", compt);
		model.addAttribute("isReward", isReward);
		model.addAttribute("rewardList", rewardList);
		model.addAttribute(NAV_BAR, CURR_MAN_BAR);
		return "comptManConfirmResultCheck";
	}

}
