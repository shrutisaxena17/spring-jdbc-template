package com.example.spring_jdbc_template.repository;

import com.example.spring_jdbc_template.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    //rowMapper to map the rows of the ResultSet to the User object
    private final RowMapper<User> rowMapper=new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
        }
    };

    //CRUD Operations
    //find all users
    public List<User> findAll(){
       String sql= "SELECT * FROM users";
       return jdbcTemplate.query(sql,rowMapper);
    }

    //find user by id
    public User findById(int id){
        String sql="SELECT * FROM users WHERE id=?";
        return jdbcTemplate.queryForObject(sql,rowMapper,id);
    }

    //insert the user into the database
    public int save(User user){
        String sql="INSERT INTO users(name,email) VALUES(?,?)";
        return jdbcTemplate.update(sql, user.getName(),user.getEmail());
    }

    //update the existing user
    public int update(User user){
        String sql="UPDATE users SET name=?, email=? WHERE id=? ";
        return jdbcTemplate.update(sql,user.getName(),user.getEmail(),user.getId());
    }

    public int deleteById(int id){
        String sql="DELETE FROM users WHERE id=?";
        return jdbcTemplate.update(sql,id);
    }

}
