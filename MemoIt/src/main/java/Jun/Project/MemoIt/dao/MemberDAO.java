package Jun.Project.MemoIt.dao;

import Jun.Project.MemoIt.model.Member;

public interface MemberDAO {
	void register(Member member);
    Member login(String username, String password);

}
