package com.spring.spring_board.controller;



import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.spring_board.dao.BDao;
import com.spring.spring_board.dao.IDao;

@Controller
public class BController {
	
	BDao dao;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	public void setDao(BDao dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value = {"/", "/list"})
	public String list(Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		model.addAttribute("list", dao.list());
		return "/list";
	}

	@RequestMapping("/write_view")
	public String write_view(Model model) {
		return "/write_view";
	}

	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		
		dao.write(request.getParameter("bName"), request.getParameter("bTitle"), request.getParameter("bContent"));
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String contentView(HttpServletRequest request, Model model) {
		dao.upHit(request.getParameter("bId"));
		IDao dao = sqlSession.getMapper(IDao.class);
		model.addAttribute("content_view", dao.contentView(request.getParameter("bId")));
		return "/content_view";
	}
	
	@RequestMapping("/modify")
	public String modify(HttpServletRequest request, Model model) {
		dao.modify(request.getParameter("bId"), request.getParameter("bName"), request.getParameter("bTitle"), request.getParameter("bContent"));
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		dao.delete(request.getParameter("bId"));
		return "redirect:list";
	}
}