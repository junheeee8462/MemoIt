package Jun.Project.MemoIt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import Jun.Project.MemoIt.model.Memo;

public class MemoRowMapper implements RowMapper<Memo> {
    @Override
    public Memo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Memo memo = new Memo();
        memo.setId(rs.getInt("id"));
        memo.setMemberId(rs.getLong("member_id"));
        memo.setContent(rs.getString("content"));
        memo.setCreatedAt(rs.getTimestamp("created_at"));
        return memo;
    }
}
