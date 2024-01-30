package service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import model.Board;
import model.Delegate;
import model.History;
import model.User;

public interface PostService {
    int findId(String id);
    int checkPassword(String id, String pwd);
    User getUserInfo(String id);
    int findSeq();
    int submitWriteForm(String id, String subject, String content, String userRank);
    int savesubmitWriteForm(String id, String subject, String content);
    List<Board> getBoardList(String id, String payOption, String userRank, String searchType,
            String searchKeyword, String startDate, String endDate);
	Board getBoardBySeq(int seq);
	void updateBoard(int seq, String id, String subject, String content);
	String getUserNameById(String checkName);
	void submitHistory(String id, int postSeq);
	List<History> getHistoryListByBoardSeq(int seq);
	void saveUpdateBoard(int seq, String id, String subject, String content);
	void checkBoard(int seq, String id, String subject, String content, String payOption, String loginId);
	void submitCheckHistory(String loginId, int postSeq, String payOption);
	void rejectBoardseq(int seq, String loginId);
	void submitSaveHistory(String id, int postSeq);
	List<String> getDelegatesByRank(String loggedInUserRank);
	List<Map<String, Object>> getDelegateInfoByIds(List<String> delegateList);
	String getDelegateRankByIds(String delegateId);
	int approveDelegate(String loggedInUserId, String delegateId);
	Delegate getDelegateInfo(String id);
	List<Board> getBoardList2(String id, String payOption, String userRank, String searchType, String searchKeyword,
			String startDate, String endDate, String delegateUserRank, Date grantStartTime, Date grantEndTime, String delegateapproverid);
	Delegate getDelegatecheckInfo(String id);
	int submitWriteFormD(String id, String subject, String content, String userRank, String delegateApproverRank,
			String delegateApproverId);
	void submitHistoryD(String id, int postSeq, String delegateApproverId, String delegateApproverRank);
	void checkBoardD(int seq, String id, String subject, String content, String payOption, String loginId,
			String approverId);
}
