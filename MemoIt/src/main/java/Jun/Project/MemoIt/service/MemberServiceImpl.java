package Jun.Project.MemoIt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import Jun.Project.MemoIt.dao.MemberDAO;
import Jun.Project.MemoIt.model.Member;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void register(Member member) {
        // 아이디 중복 확인
        if (isUsernameExists(member.getUsername())) {
            throw new IllegalArgumentException("동일한 아이디가 존재합니다");
        }
        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);
        memberDAO.register(member);
    }

    @Override
    public Member login(String username, String password) {
        Member member = memberDAO.findByUsername(username);
        
        // 아이디가 없거나 비밀번호가 일치하지 않으면 null 반환
        if (member == null || !passwordEncoder.matches(password, member.getPassword())) {
            return null;
        }
        
        return member;
    }

    // 아이디 중복 확인 메서드
    public boolean isUsernameExists(String username) {
        return memberDAO.isUsernameExists(username);
    }
}
