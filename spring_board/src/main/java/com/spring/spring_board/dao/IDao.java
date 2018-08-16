package com.spring.spring_board.dao;

import java.util.ArrayList;

import com.spring.spring_board.dto.BDto;

public interface IDao {
	
	public ArrayList<BDto> list();
	public void write(String bName, String bTitle, String bContent);
	public void modify(String bId, String bName, String bTitle, String bContent);
	public BDto contentView(String strID);
	public void delete(String bId);
	public void upHit(String bId);
	
}
