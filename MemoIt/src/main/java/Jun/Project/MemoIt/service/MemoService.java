package Jun.Project.MemoIt.service;

import java.util.List;
import java.util.Map;

import Jun.Project.MemoIt.model.Memo;

public interface MemoService {
    void addMemo(Memo memo);
    List<Memo> getMemosByMemberId(Long memberId);
    void deleteMemoById(int memoId, Long memberId);
	Map<String, List<Memo>> getCategorizedMemos(Long long1);

}
