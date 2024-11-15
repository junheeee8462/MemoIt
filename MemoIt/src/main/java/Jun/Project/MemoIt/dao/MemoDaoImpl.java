package Jun.Project.MemoIt.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import Jun.Project.MemoIt.model.Memo;

@Repository
public class MemoDaoImpl implements MemoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addMemo(Memo memo) {
        String sql = "INSERT INTO memo (member_id, content) VALUES (?, ?)";
        jdbcTemplate.update(sql, memo.getMemberId(), memo.getContent());
    }

    @Override
    public List<Memo> getMemosByMemberId(Long memberId) {
        String sql = "SELECT * FROM memo WHERE member_id = ?";
        return jdbcTemplate.query(sql, new Object[]{memberId}, new MemoRowMapper());
    }
    
    @Override
    public void deleteMemoById(int memoId, Long memberId) {
        String sql = "DELETE FROM memo WHERE id = ? AND member_id = ?";
        jdbcTemplate.update(sql, memoId, memberId);
    }

	@Override
	public List<Memo> findByMemberId(Long memberId) {
        String sql = "SELECT * FROM memo WHERE member_id = ?";
        return jdbcTemplate.query(sql, new Object[]{memberId}, new MemoRowMapper());
    }

}
