package Jun.Project.MemoIt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import Jun.Project.MemoIt.model.Member;


@Repository
public class MemberDAOImpl implements MemberDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 회원가입
    @Override
    public void register(Member member) {
        // 주의: 암호화된 비밀번호 사용
        String sql = "INSERT INTO MEMBER (USERNAME, PASSWORD) VALUES (?, ?)";
        jdbcTemplate.update(sql, member.getUsername(), member.getPassword());
    }

    // 로그인
    @Override
    public Member login(String username, String encryptedPassword) {
        String sql = "SELECT * FROM MEMBER WHERE USERNAME = ? AND PASSWORD = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username, encryptedPassword},
                (rs, rowNum) -> {
                    Member member = new Member();
                    member.setId(rs.getLong("ID"));
                    member.setUsername(rs.getString("USERNAME"));
                    member.setPassword(rs.getString("PASSWORD"));
                    
                    return member;
                });
    }
}
