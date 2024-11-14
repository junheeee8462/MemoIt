package Jun.Project.MemoIt.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Jun.Project.MemoIt.dao.MemoDao;
import Jun.Project.MemoIt.model.Memo;

@Service
public class MemoServiceImpl implements MemoService {

    @Autowired
    private MemoDao memoDao;

    @Override
    public void addMemo(Memo memo) {
        memoDao.addMemo(memo);
    }

    @Override
    public List<Memo> getMemosByMemberId(Long memberId) {
        return memoDao.getMemosByMemberId(memberId);
    }
    
    @Override
    public void deleteMemoById(int memoId, Long memberId) {
        memoDao.deleteMemoById(memoId, memberId);
    }
    
    public Map<String, List<Memo>> getCategorizedMemos(Long memberId) {
        List<Memo> allMemos = memoDao.findByMemberId(memberId);

        Map<String, List<Memo>> categorizedMemos = new HashMap<>();
        List<Memo> todayMemos = new ArrayList<>();
        List<Memo> yesterdayMemos = new ArrayList<>();
        List<Memo> last7DaysMemos = new ArrayList<>();
        List<Memo> lastMonthMemos = new ArrayList<>();

        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalDate lastWeek = today.minusDays(7);
        LocalDate lastMonth = today.minusMonths(1);

        for (Memo memo : allMemos) {
            // Date를 LocalDate로 변환
            LocalDate memoDate = memo.getCreatedAt().toInstant()
                                       .atZone(ZoneId.systemDefault())
                                       .toLocalDate();

            if (memoDate.isEqual(today)) {
                todayMemos.add(memo);
            } else if (memoDate.isEqual(yesterday)) {
                yesterdayMemos.add(memo);
            } else if (memoDate.isAfter(lastWeek)) {
                last7DaysMemos.add(memo);
            } else if (memoDate.isAfter(lastMonth)) {
                lastMonthMemos.add(memo);
            }
        }

        categorizedMemos.put("오늘", todayMemos);
        categorizedMemos.put("어제", yesterdayMemos);
        categorizedMemos.put("지난 7일", last7DaysMemos);
        categorizedMemos.put("지난 달", lastMonthMemos);

        return categorizedMemos;
    }


}
