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
	public Member findByUsername(String username) {
	    String sql = "SELECT * FROM MEMBER WHERE USERNAME = ?";
	    return jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rowNum) -> {
	        Member member = new Member();
	        member.setId(rs.getLong("ID"));
	        member.setUsername(rs.getString("USERNAME"));
	        member.setPassword(rs.getString("PASSWORD"));
	        return member;
	    });
	}

	// 중복 아이디 확인
    public boolean isUsernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM MEMBER WHERE USERNAME = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{username}, Integer.class);
        return count != null && count > 0;
    }
}
