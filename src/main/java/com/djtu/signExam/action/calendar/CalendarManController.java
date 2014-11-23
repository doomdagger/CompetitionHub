package com.djtu.signExam.action.calendar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.djtu.signExam.config.ProjectPageConfig;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TComptCalendar;
import com.djtu.signExam.service.calendar.CalendarService;
import com.djtu.signExam.util.CompressPicUtils;
import com.djtu.signExam.util.StringConst;
import com.djtu.signExam.util.UploadConst;


@Controller
@RequestMapping("/man/admin/calendar")
public class CalendarManController {
	
	private static final String NAV = "navbar";
	private static final String CAL = "calendar";
	
	@Autowired
	private CalendarService calService;
	
	@RequestMapping("/list")
	public String list(){
		return "redirect:/man/admin/calendar/list/1";
	}
	
	/*
	 * 分页显示calendar
	 */
	@RequestMapping("/list/{currpage}")
	public String listInPage(@PathVariable Integer currpage,Model model){
		Pageable pageable = Pageable.inPage(currpage, ProjectPageConfig.MAN_CAL_LIST_PAGESIZE);
		List<TComptCalendar> calList = calService.getAllListInPage(pageable);
		
		model.addAttribute("pageable", pageable);
		model.addAttribute("calList", calList);
		model.addAttribute(NAV, CAL);
		return "adminCalendarList";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,@RequestParam MultipartFile cover,@RequestParam String title,@RequestParam String content,@RequestParam String date){
		
		//上传并压缩图片--------start-----------
        String savePath = request.getServletContext().getRealPath(UploadConst.CAL_RES_PATH);
        
        if (!new File(savePath).isDirectory())
        {
        	new File(savePath).mkdirs();
        }
        String urlPath = "";
        try {
        	//获取上传文件旧名
            String name = cover.getOriginalFilename();
            //获取后缀名
            String suffix = name.substring(name.lastIndexOf(".")+1);
            //创建上传目标文件
            String newFileName = "COVER"+System.currentTimeMillis() + new Random(50000).nextInt() + "."+suffix;
            File targetFile = new File(savePath,newFileName);
            //获得保存数据库的http访问路径
            urlPath = UploadConst.CAL_URL_PATH.replaceAll("\\\\", "/")+newFileName;
            //创建输入输出流
        	InputStream is = cover.getInputStream();
        	FileOutputStream fos = new FileOutputStream(targetFile);
        	FileCopyUtils.copy(is, fos);
        	//对上传图片进行压缩
			CompressPicUtils.compressPic(targetFile, targetFile, UploadConst.CAL_COVER_WIDTH, UploadConst.CAL_COVER_HEIGHT, true);
			//打开上传图片文件夹，调试用
			//Runtime.getRuntime().exec("cmd /c start "+savePath);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("压缩图片失败");
		}
        
        TComptCalendar cal = new TComptCalendar();
        cal.setCover(urlPath);
        cal.setCreatetime(new Date());
        cal.setOnDate(date);
        cal.setTitle(title);
        cal.setContent(content);
        cal.setIsDelete(false);
        cal.setIsValid(true);
        calService.addOne(cal);
		return "redirect:/man/admin/calendar/list";
	}
	
	/*
	 * 根据Id删除一个赛事日程
	 */
	@RequestMapping("/delete")
	public @ResponseBody String delete(@RequestParam String calId){
		return calService.deleteOneById(calId)?StringConst.AJAX_SUCCESS:StringConst.AJAX_FAIL;
	}

	/*
	 * 优先一个赛事日程
	 */
	@RequestMapping("/doPriority")
	public @ResponseBody String doPriority(@RequestParam String calId){
		return calService.priorityOneById(calId)?StringConst.AJAX_SUCCESS:StringConst.AJAX_FAIL;
	}
}
