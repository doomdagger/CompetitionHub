package com.djtu.signExam.service.show;

import java.util.List;

import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TShow;
import com.djtu.signExam.model.TShowAlbum;

public interface ShowService {
	
	public TShow getShowById(String showId);
	
	public List<TShow> getAllShowInPage(Pageable pageable);
	
	public List<TShow> getAllShowByAdminIdInPage(String adminId,Pageable pageable);
	
	public List<TShowAlbum> getAllPicByShowId(String showId);
	
	public void addOneShow(TShow show);
	
	public void addOnePic(TShowAlbum pic);
	
	public boolean deleteOneShowById(String showId);
	
	public boolean deleteOnePicById(String picId);
	
	public boolean setTheCover(String showId,String cover);
	
	public boolean doPrrorityPic(String picId);
	

}
