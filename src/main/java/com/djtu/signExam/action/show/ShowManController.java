package com.djtu.signExam.action.show;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.djtu.signExam.config.ProjectPageConfig;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TShow;
import com.djtu.signExam.model.TShowAlbum;
import com.djtu.signExam.service.show.ShowService;
import com.djtu.signExam.util.CompressPicUtils;
import com.djtu.signExam.util.SessionConst;
import com.djtu.signExam.util.SessionUtil;
import com.djtu.signExam.util.StringConst;
import com.djtu.signExam.util.UploadConst;


@Controller
@RequestMapping("/man/show")
public class ShowManController {
	
	private static final String NAV = "navbar";
	private static final String SHOW = "show";
	
	@Autowired
	private ShowService showService;
	

	/*
	 * 分页显示show list
	 */
    @RequestMapping(value = {"/","list"})
    public String list(Model model){
        return "redirect:/man/show/list/1";
    }

    @RequestMapping(value = "/list/{currpage}")
    public String listInPage(@PathVariable Integer currpage,HttpServletRequest request,Model model){
    	//获取当前登陆用户ID
    	Integer adminId = (Integer) SessionUtil.getValue(request, SessionConst.U_USER,SessionConst.U_USER_LINK);
    	//System.out.println("amdin ID:"+adminId +" after to string:"+adminId.toString());
    	Pageable pageable = Pageable.inPage(currpage, ProjectPageConfig.MAN_SHOW_LIST_PAGESIZE);
    	List<TShow> showList = showService.getAllShowByAdminIdInPage(adminId.toString(), pageable);
    	//add to model attribute
    	model.addAttribute(NAV, SHOW);
    	model.addAttribute("pageable", pageable);
    	model.addAttribute("showList", showList);
        return "adminShowList";
    }
    
    /*
     * 一个show下面的pic list
     */
    @RequestMapping("/album/{showLink}")
    public String album(@PathVariable String showLink,Model model){
    	List<TShowAlbum> picList = showService.getAllPicByShowId(showLink);
    	
    	model.addAttribute("showLink", showLink);
    	model.addAttribute("picList", picList);
    	return "adminShowAlbumList";
    }
    
    /*
     * 添加一个show
     */
    @RequestMapping(value = "/addShow", method = RequestMethod.POST)
    public String addShow(@RequestParam String title,@RequestParam String description,@RequestParam MultipartFile coverImage,HttpServletRequest request){
    	
    	//上传并压缩图片--------start-----------
        String savePath = request.getServletContext().getRealPath(UploadConst.SHOW_RES_PATH);
        
        if (!new File(savePath).isDirectory())
        {
        	new File(savePath).mkdirs();
        }
        String urlPath = "";
        try {
        	//获取上传文件旧名
            String name = coverImage.getOriginalFilename();
            //获取后缀名
            String suffix = name.substring(name.lastIndexOf(".")+1);
            //创建上传目标文件
            String newFileName = "COVER"+System.currentTimeMillis() + new Random(50000).nextInt() + "."+suffix;
            File targetFile = new File(savePath,newFileName);
            //获得保存数据库的http访问路径
            urlPath = UploadConst.SHOW_URL_PATH.replaceAll("\\\\", "/")+newFileName;
            //创建输入输出流
        	InputStream is = coverImage.getInputStream();
        	FileOutputStream fos = new FileOutputStream(targetFile);
        	FileCopyUtils.copy(is, fos);
        	//对上传图片进行压缩
			CompressPicUtils.compressPic(targetFile, targetFile, UploadConst.SHOW_COVER_WIDTH, UploadConst.SHOW_COVER_HEIGHT, true);
			//打开上传图片文件夹，调试用
			//Runtime.getRuntime().exec("cmd /c start "+savePath);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("压缩图片失败");
		}
        //上传并压缩图片--------end-----------
        //获取当前登陆用户ID
    	Integer adminId = (Integer) SessionUtil.getValue(request, SessionConst.U_USER,SessionConst.U_USER_LINK);
    	String adminer = (String) SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_NAME);
    	TShow show = new TShow();
    	show.setTitle(title);
    	show.setDescription(description);
    	show.setCreatetime(new Date());
    	show.setCoverImage(urlPath);
    	show.setSkTAdmin(adminId);
    	show.setAdminer(adminer);
    	showService.addOneShow(show);
    	return "redirect:/man/show/list";
    }
    
    /*
     * 添加一个pic
     */
    @RequestMapping(value = "/addPic", method = RequestMethod.POST)
    public String addPic(HttpServletRequest request,@RequestParam String description,@RequestParam MultipartFile image,@RequestParam Integer showLink){
    	
    	//上传并压缩图片--------start-----------
        String savePath = request.getServletContext().getRealPath(UploadConst.SHOW_RES_PATH);
        
        if (!new File(savePath).isDirectory())
        {
        	new File(savePath).mkdirs();
        }
        String urlPath = "";
        try {
        	//获取上传文件旧名
            String name = image.getOriginalFilename();
            //获取后缀名
            String suffix = name.substring(name.lastIndexOf(".")+1);
            //创建上传目标文件
            String newFileName = "PIC"+System.currentTimeMillis() + new Random(50000).nextInt() + "."+suffix;
            File targetFile = new File(savePath,newFileName);
            //获得保存数据库的http访问路径
            urlPath = UploadConst.SHOW_URL_PATH.replaceAll("\\\\", "/")+newFileName;
            //创建输入输出流
        	InputStream is = image.getInputStream();
        	FileOutputStream fos = new FileOutputStream(targetFile);
        	FileCopyUtils.copy(is, fos);
        	//对上传图片进行压缩
			CompressPicUtils.compressPic(targetFile, targetFile, UploadConst.SHOW_IMG_WIDTH, UploadConst.SHOW_IMG_HEIGHT, true);
			//打开上传图片文件夹，调试用
			//Runtime.getRuntime().exec("cmd /c start "+savePath);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("压缩图片失败");
		}
    	
    	//获取当前登陆用户ID
    	Integer adminId = (Integer) SessionUtil.getValue(request, SessionConst.U_USER,SessionConst.U_USER_LINK);
    	TShowAlbum pic = new TShowAlbum();
    	pic.setCreatetime(new Date());
    	pic.setDescription(description);
    	pic.setImage(urlPath);
    	pic.setSkTShow(showLink);
    	showService.addOnePic(pic);
    	return "redirect:/man/show/album/"+showLink;
    }
    
    /*
     * 删除一个show
     * 同时要删除所有的showID下的pic
     */
    @RequestMapping("/deleteOneShow")
    public @ResponseBody String deleteOneShow(@RequestParam String showLink){
    	return showService.deleteOneShowById(showLink)?StringConst.AJAX_SUCCESS:StringConst.AJAX_FAIL;
    }
    
    /*
     * 删除一个pic
     */
    @RequestMapping("/deleteOnePic")
    public @ResponseBody String deleteOnePic(@RequestParam String picLink){
    	return showService.deleteOnePicById(picLink)?StringConst.AJAX_SUCCESS:StringConst.AJAX_FAIL;
    }
    
    /*
     * 优先一个pic
     */
    @RequestMapping("/doPriorityOnePic")
    public @ResponseBody String doPriorityOneShow(@RequestParam String picLink){
    	return showService.doPrrorityPic(picLink)?StringConst.AJAX_SUCCESS:StringConst.AJAX_FAIL;
    }

}
