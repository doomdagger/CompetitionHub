package com.djtu.signExam.action.ads;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

import com.djtu.signExam.model.TAds;
import com.djtu.signExam.service.ads.AdsService;
import com.djtu.signExam.util.CompressPicUtils;
import com.djtu.signExam.util.StringConst;
import com.djtu.signExam.util.UploadConst;


@Controller
@RequestMapping("/man/admin/ads")
public class AdsManController {

	private static final String NAV = "navbar";
	private static final String ADS = "ads";
	
	@Autowired
	private AdsService adsService;
	
	@RequestMapping("/list")
	public String list(Model model){
		List<TAds> adList = adsService.getAllAds();
		model.addAttribute("adList", adList);
		model.addAttribute(NAV, ADS);
		return "adminAdvertisement";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request,HttpServletResponse response,@RequestParam MultipartFile uploadFile,@RequestParam String title,@RequestParam String hrefLink){
		
		//项目绝对路径
        String savePath = request.getServletContext().getRealPath(UploadConst.ADS_RES_PATH);
        
        if (!new File(savePath).isDirectory()) 
        {
        	new File(savePath).mkdirs();
        }
        String urlPath = "";
        try {
        	//获取上传文件旧名
            String name = uploadFile.getOriginalFilename();
            //获取后缀名
            String suffix = name.substring(name.lastIndexOf(".")+1);
            //创建上传目标文件
            String newFileName = System.currentTimeMillis() + new Random(50000).nextInt() + "."+suffix;
            File targetFile = new File(savePath,newFileName);
            //获得保存数据库的http访问路径
            urlPath = UploadConst.ADS_URL_PATH.replaceAll("\\\\", "/")+newFileName;
            //创建输入输出流
        	InputStream is = uploadFile.getInputStream();
        	FileOutputStream fos = new FileOutputStream(targetFile);
        	FileCopyUtils.copy(is, fos);
        	//对上传图片进行压缩
			CompressPicUtils.compressPic(targetFile, targetFile, UploadConst.ADS_IMG_WIDTH, UploadConst.ADS_IMG_HEIGHT, true);
			//打开上传图片文件夹，调试用
			//Runtime.getRuntime().exec("cmd /c start "+savePath);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("压缩图片失败");
		}
		TAds ad = new TAds();
		ad.setHref(hrefLink);
		ad.setCreatetime(new Date());
		ad.setTitle(title);
		ad.setImage(urlPath);
		adsService.addOneAd(ad);
		return "redirect:/man/admin/ads/list";
	}
	
	@RequestMapping("/delete")
	public @ResponseBody String delete(@RequestParam String adLink){
		return adsService.deleteOneAdById(adLink)?StringConst.AJAX_SUCCESS:StringConst.AJAX_FAIL;
	}
	
	@RequestMapping("/doPriority")
	public @ResponseBody String doPriority(@RequestParam String adLink){
		return adsService.doPriority(adLink)?StringConst.AJAX_SUCCESS:StringConst.AJAX_FAIL;
	}
}
