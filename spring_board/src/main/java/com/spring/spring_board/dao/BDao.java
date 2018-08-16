package com.spring.spring_board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.spring.spring_board.dto.BDto;

public class BDao {

	
	JdbcTemplate template = null;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public BDao() {
	}

	public ArrayList<BDto> list() {
		
		String query = "select bId, bName, bTitle, bContent, bDate, bHit from mvc_board order by bId";
		
		return (ArrayList<BDto>)template.query(query, new BeanPropertyRowMapper<BDto>(BDto.class));

	}

	public void write(final String bName, final String bTitle, final String bContent) {
		// TODO Auto-generated method stub
		
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				
				String query = "insert into mvc_board (bId, bName, bTitle, bContent, bHit) values (mvc_board_seq.nextval, ?, ?, ?, 0)";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);
				
				return pstmt;
			}
		});
	}

	public BDto contentView(String strID) {
		// TODO Auto-generated method stub

		upHit(strID);
		
		String query = "select * from mvc_board where bId = " + strID;
		
		return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));

	}

	public void modify(final String bId, final String bName, final String bTitle, final String bContent) {
		// TODO Auto-generated method stub
		
		String query = "update mvc_board set bName = ?, bTitle = ?, bContent = ? where bId = ?";
		template.update(query, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, bName);
				ps.setString(2, bTitle);
				ps.setString(3, bContent);
				ps.setInt(4, Integer.parseInt(bId));
			}
			
		});
	}

	public void delete(final String bId) {
		// TODO Auto-generated method stub
		
		String query = "delete from mvc_board where bId = ?";
		template.update(query, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, bId);
			}
			
		});
	}
	
	public void upHit(final String bId) {
		// TODO Auto-generated method stub
		
		String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
		
		template.update(query, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, Integer.parseInt(bId));
			}
			
		});
	}
}
