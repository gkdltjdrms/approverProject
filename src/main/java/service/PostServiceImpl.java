package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import dao.PostDao;
import model.Board;
import model.Delegate;
import model.History;
import model.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("PostService")
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Override
    public int findId(String id) {
        return postDao.findId(id);
    }

    @Override
    public int checkPassword(String id, String pwd) {
        return postDao.checkPassword(id, pwd);
    }

    @Override
    public User getUserInfo(String id) {
        return postDao.getUserInfo(id);
    }
    
	@Override
	public Delegate getDelegateInfo(String id) {
		// TODO Auto-generated method stub
		  return postDao.getDelegateInfo(id);
	}
    @Override
    public int findSeq() {
        return postDao.findSeq();
    }

    @Override
    public int submitWriteForm(String id, String subject, String content, String userRank) {
        return postDao.submitWriteForm(id, subject, content, userRank);
    }
    
    @Override
	public int submitWriteFormD(String id, String subject, String content, String userRank, String delegateApproverRank,
			String delegateApproverId) {
		// TODO Auto-generated method stub
    	  return postDao.submitWriteFormD(id, subject, content, userRank, delegateApproverRank, delegateApproverId);
	}

    @Override
    public int savesubmitWriteForm(String id, String subject, String content) {
        return postDao.savesubmitWriteForm(id, subject, content);
    }
    
    @Override
	public void updateBoard(int seq, String id, String subject, String content) {
		// TODO Auto-generated method stub
    	 postDao.updateBoard(seq, id, subject, content);
	}
    
    @Override
	public void rejectBoardseq(int seq, String loginId) {
		// TODO Auto-generated method stub
    	 postDao.rejectBoardseq(seq, loginId);
	}
    
    @Override
	public void checkBoard(int seq, String id, String subject, String content, String payOption, String loginId) {
		// TODO Auto-generated method stub
    	 postDao.checkBoard(seq, id, subject, content, payOption, loginId);
	}
    
	@Override
	public void checkBoardD(int seq, String id, String subject, String content, String payOption, String loginId,
			String approverId) {
		// TODO Auto-generated method stub
		 postDao.checkBoardD(seq, id, subject, content, payOption, loginId, approverId);
	}
    
	@Override
	public void saveUpdateBoard(int seq, String id, String subject, String content) {
		// TODO Auto-generated method stub
		 postDao.saveUpdateBoard(seq, id, subject, content);
	}

    @Override
    public List<Board> getBoardList(String id, String payOption, String userRank, String searchType,
                                    String searchKeyword, String startDate, String endDate) {
        return postDao.getBoardList(id, payOption, userRank, searchType, searchKeyword, startDate, endDate);
    }
	@Override
	public List<Board> getBoardList2(String id, String payOption, String userRank, String searchType,
			String searchKeyword, String startDate, String endDate, String delegateUserRank, Date grantStartTime,
			Date grantEndTime, String delegateapproverid) {
		// TODO Auto-generated method stub
		return postDao.getBoardList2(id, payOption, userRank, searchType, searchKeyword, startDate, endDate, delegateUserRank, 
				grantStartTime, grantEndTime, delegateapproverid
				);
	}

	@Override
	public Board getBoardBySeq(int seq) {
		// TODO Auto-generated method stub
		return postDao.getBoardBySeq(seq);
	}

	@Override
	public String getUserNameById(String checkName) {
		// TODO Auto-generated method stub
		return postDao.getUserNameById(checkName);
	}

	@Override
	public void submitHistory(String id, int PostSeq) {
		// TODO Auto-generated method stub
		 postDao.submitHistory(id, PostSeq);
	}
	
	@Override
	public void submitHistoryD(String id, int postSeq, String delegateApproverId, String delegateApproverRank) {
		// TODO Auto-generated method stub
		postDao.submitHistoryD(id, postSeq, delegateApproverId, delegateApproverRank);
	}

	@Override
	public List<History> getHistoryListByBoardSeq(int seq) {
		// TODO Auto-generated method stub
		return postDao.getHistoryListByBoardSeq(seq);
	}

	@Override
	public void submitCheckHistory(String loginId, int postSeq, String payOption) {
		// TODO Auto-generated method stub
		postDao.submitCheckHistory(loginId, postSeq, payOption);
	}

	@Override
	public void submitSaveHistory(String id, int postSeq) {
		// TODO Auto-generated method stub
		 postDao.submitSaveHistory(id, postSeq);
	}

	@Override
	public List<String> getDelegatesByRank(String loggedInUserRank) {
		// TODO Auto-generated method stub
		 return postDao.getDelegatesByRank(loggedInUserRank);
	}

	@Override
	public List<Map<String, Object>> getDelegateInfoByIds(List<String> delegateList) {
		// TODO Auto-generated method stub
		return postDao.getDelegateInfoByIds(delegateList);
	}

	@Override
	public String getDelegateRankByIds(String delegateId) {
		// TODO Auto-generated method stub
		return postDao.getDelegateRankByIds(delegateId);
	}

	@Override
	public int approveDelegate(String loggedInUserId, String delegateId) {
		// TODO Auto-generated method stub
		return postDao.approveDelegate(loggedInUserId, delegateId);
	}

	@Override
	public Delegate getDelegatecheckInfo(String id) {
		// TODO Auto-generated method stub
		return postDao.getDelegatecheckInfo(id);
	}






	





	

	

	



	
}
