package Jun.Project.MemoIt.dao;

import java.util.List;

import Jun.Project.MemoIt.model.Memo;

public interface MemoDao {

	void addMemo(Memo memo);
    List<Memo> getMemosByMemberId(Long memberId);
    void deleteMemoById(int memoId, Long memberId);

}
