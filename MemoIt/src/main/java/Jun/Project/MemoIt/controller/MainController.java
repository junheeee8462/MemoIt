package Jun.Project.MemoIt.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Jun.Project.MemoIt.model.Member;
import Jun.Project.MemoIt.model.Memo;
import Jun.Project.MemoIt.service.MemberService;
import Jun.Project.MemoIt.service.MemoService;

@Controller
public class MainController {

    @Autowired
    private MemoService memoService;

    @Autowired
    private MemberService memberService;

    // 메인 페이지
    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            // 로그인된 사용자 정보와 해당 사용자의 메모 리스트 및 분류된 메모 리스트를 모델에 추가
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("userId", loggedInUser.getUsername()); // 유저 ID 추가
            
            // 일반 메모 리스트
            List<Memo> memos = memoService.getMemosByMemberId(loggedInUser.getId());
            model.addAttribute("memos", memos);

            // 분류된 메모 리스트
            Map<String, List<Memo>> categorizedMemos = memoService.getCategorizedMemos(loggedInUser.getId());
            model.addAttribute("categorizedMemos", categorizedMemos);
               
            
        }
        return "home";
    }

    // 메모 추가 기능
    @PostMapping("/addMemo")
    public String addMemo(@RequestParam("content") String content, HttpSession session) {
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            Memo memo = new Memo();
            memo.setMemberId(loggedInUser.getId());
            memo.setContent(content);
            memoService.addMemo(memo);
        }
        return "redirect:/"; // 메모 추가 후 메인 페이지로 리다이렉트
    }

    // 회원가입 페이지 표시
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String register(Member member, Model model) {
        memberService.register(member);
        model.addAttribute("message", "Registration successful!");
        return "login";
    }

    // 로그인 페이지 표시
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, 
                        @RequestParam("password") String password,
                        HttpSession session, 
                        Model model) {
        Member member = memberService.login(username, password);
        if (member != null) {
            session.setAttribute("loggedInUser", member);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // 메모 삭제 기능
    @PostMapping("/deleteMemo")
    public String deleteMemo(@RequestParam("memoId") int memoId, HttpSession session) {
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            memoService.deleteMemoById(memoId, loggedInUser.getId());
        }
        return "redirect:/";
    }
}
