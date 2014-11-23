package com.djtu.signExam.action.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.djtu.signExam.model.TSignin;
import com.djtu.signExam.service.search.SearchService;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/")
	public String search(){
		return "search";
	}
	
	/*
	 * 根据用户提供的email进行查询
	 */
	@RequestMapping(value = "/doSearch",method = RequestMethod.POST)
	public String searchResult(@RequestParam String keyword,Model model){
		
		List<TSignin> result;
		if(keyword.indexOf("@")<0 || keyword.indexOf(".com")<0){
			//根据名字查找
			result = searchService.doSearchForRewardSignByName(keyword);
		}else{
			result = searchService.doSearchForRewardSignByEmail(keyword);
		}
		model.addAttribute("result", result);
		model.addAttribute("keyword", keyword);
		return "searchResult";
	}

}
