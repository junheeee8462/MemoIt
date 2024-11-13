package Jun.Project.MemoIt.service;

import Jun.Project.MemoIt.model.Member;

public interface MemberService {
	void register(Member member);
    Member login(String username, String password);

}
