package Jun.Project.MemoIt.service;

import java.util.List;

import Jun.Project.MemoIt.model.Memo;

public interface MemoService {
    void addMemo(Memo memo);
    List<Memo> getMemosByMemberId(Long memberId);
    void deleteMemoById(int memoId, Long memberId);

}
