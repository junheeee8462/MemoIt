package Jun.Project.MemoIt.service;

import java.util.List;
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

}
