package Jun.Project.MemoIt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Jun.Project.MemoIt.dao.MemberDAO;
import Jun.Project.MemoIt.model.Member;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDAO memberDAO;

    @Override
    public void register(Member member) {
        String encryptedPassword = encryptPassword(member.getPassword());
        member.setPassword(encryptedPassword);
        memberDAO.register(member);
    }

    @Override
    public Member login(String username, String password) {
        String encryptedPassword = encryptPassword(password);
        return memberDAO.login(username, encryptedPassword);
    }

    // 암호화 메서드
    private String encryptPassword(String password) {
        //아직 구현되어있지 않
        return password; //실제 구현 시 암호화된 문자열 반환
    }
}
