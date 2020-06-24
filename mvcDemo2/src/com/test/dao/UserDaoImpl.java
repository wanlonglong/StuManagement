package com.test.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.test.domain.Student;
import com.test.domain.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User loginUser(User user) {
		try {
			String sql="select * from t_user where username=? and password=?";
		    return jdbcTemplate.queryForObject(sql, new MyRowMapper_user(), user.getUsername(), user.getPassword());		
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
			
	}
	
	public int findCount() {
		try {
			String sql = "select count(*) from t_student ";
		   return jdbcTemplate.queryForObject(sql, Integer.class);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	public List<Student> findPage(int begin, int pageSize) {
		try {
			String sql = "select * from t_student limit ?,?";
			return jdbcTemplate.query(sql, new MyRowMapper_stu(), begin,pageSize);
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}

	@Override
	public void delete(int student_id) {
		// TODO Auto-generated method stub
		String sql = "delete from t_student where student_id = ?";
		jdbcTemplate.update(sql, student_id);
		
	}
}

class MyRowMapper_stu implements RowMapper<Student>{

	@Override
	public Student mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		int student_id=rs.getInt("student_id");
		String name=rs.getString("name");
		int semester=rs.getInt("semester");
		
		Student stu = new Student();

		stu.setStudent_id(student_id);
		stu.setName(name);
		stu.setSemester(semester);
		return stu;
	}
	
}
	
	
class MyRowMapper_user implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		String uid = rs.getString("uid");
		String username = rs.getString("username");
		String password = rs.getString("password");

		
		User user = new User();

		user.setUid(uid);
		user.setUsername(username);
		user.setPassword(password);
		
		return user;
	}
	
}


