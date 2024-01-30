package dao;

import java.util.List;
import java.util.Map;
import java.io.File;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import model.Board;
import model.BoardWrite;
import model.Delegate;
import model.History;
import model.SearchCriteria;
import model.User;
import model.post;
import oracle.jdbc.proxy.annotation.Post;

@Repository
public class PostDao {
    
    @Autowired
    private SqlSession sqlSession; // SqlSession을 주입합니다.



	public int findId(String id) {
		
		
		  return sqlSession.selectOne("mapper.BoardMapper.findId", id);
	}


	public int checkPassword(String id, String pwd) {
	    Map<String, String> parameters = new HashMap<>();
	    parameters.put("id", id);
	    parameters.put("pwd", pwd);
	    
	    return sqlSession.selectOne("mapper.BoardMapper.checkPassword", parameters);
	}


	public User getUserInfo(String id) {
		// TODO Auto-generated method stub
		 return sqlSession.selectOne("mapper.BoardMapper.getUserInfo", id);
	}
	
	public Delegate getDelegateInfo(String id) {
		// TODO Auto-generated method stub
		 return sqlSession.selectOne("mapper.BoardMapper.getDelegateInfo", id);
	}



	public int getSeqValue() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.BoardMapper.getSeqValue");
	}


	public int findSeq() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.BoardMapper.findSeq");
	}


	public int submitWriteForm(String id, String subject, String content, String userRank) {
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("id", id);
	    paramMap.put("subject", subject);
	    paramMap.put("content", content);
	    paramMap.put("userRank", userRank);
	    paramMap.put("regDate", new Date()); // 작성일 설정
	    
	    sqlSession.insert("mapper.BoardMapper.insertBoard", paramMap);
	    
	    // 삽입 후, paramMap에는 자동으로 생성된 seq 값이 들어있습니다.
	    return (int) paramMap.get("seq");
	}
	
	public int submitWriteFormD(String id, String subject, String content, String userRank,
			String delegateApproverRank, String delegateApproverId) {
		
		
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("delegateApproverRank", delegateApproverRank);
	    paramMap.put("delegateApproverId", delegateApproverId);
	    paramMap.put("id", id);
	    paramMap.put("subject", subject);
	    paramMap.put("content", content);
	    paramMap.put("userRank", userRank);
	    paramMap.put("regDate", new Date()); // 작성일 설정
	    
	    sqlSession.insert("mapper.BoardMapper.insertBoardD", paramMap);
	    
	    // 삽입 후, paramMap에는 자동으로 생성된 seq 값이 들어있습니다.
	    return (int) paramMap.get("seq");
	}




	public int savesubmitWriteForm(String id, String subject, String content) {
		// TODO Auto-generated method stub
		  Board board = new Board();
		    board.setId(id);
		    board.setSubject(subject);
		    board.setContent(content);
		    board.setRegDate(new Date()); // 작성일 설정
		    sqlSession.insert("mapper.BoardMapper.saveBoard", board);
		    return board.getSeq(); // seq 값 반환
	}
	
	public void updateBoard(int seq, String id, String subject, String content) {
		// TODO Auto-generated method stub
		  Board board = new Board();
		    board.setId(id);
		    board.setSeq(seq);
		    board.setSubject(subject);
		    board.setContent(content);
		    board.setRegDate(new Date()); // 작성일 설정
		    sqlSession.update("mapper.BoardMapper.updateBoard", board);
	}
	
	public void rejectBoardseq(int seq, String loginId) {
		// TODO Auto-generated method stub
		 Map<String, Object> paramMap = new HashMap<>();
		    paramMap.put("id", loginId);
		    paramMap.put("seq", seq);
		    
		    sqlSession.update("mapper.BoardMapper.rejectBoardseq", paramMap);
	}

	

	public void checkBoard(int seq, String id, String subject, String content, String payOption, String loginId) {
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("id", id);
	    paramMap.put("seq", seq);
	    paramMap.put("subject", subject);
	    paramMap.put("content", content);
	    paramMap.put("payOption", payOption);
	   

	    paramMap.put("regDate", new Date()); // 작성일 설정
	    paramMap.put("loginId", loginId);

	    sqlSession.update("mapper.BoardMapper.checkBoard", paramMap);
	}
	
	public void checkBoardD(int seq, String id, String subject, String content, String payOption, String loginId,
			String approverId) {
		// TODO Auto-generated method stub
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("id", id);
	    paramMap.put("seq", seq);
	    paramMap.put("subject", subject);
	    paramMap.put("content", content);
	    paramMap.put("payOption", payOption);
	    paramMap.put("approverId", approverId);
	    paramMap.put("regDate", new Date()); // 작성일 설정
	    paramMap.put("loginId", loginId);

	    sqlSession.update("mapper.BoardMapper.checkBoardD", paramMap);
	}


	
	public void saveUpdateBoard(int seq, String id, String subject, String content) {
		// TODO Auto-generated method stub
		  Board board = new Board();
		    board.setId(id);
		    board.setSeq(seq);
		    board.setSubject(subject);
		    board.setContent(content);
		    board.setRegDate(new Date()); // 작성일 설정
		    sqlSession.update("mapper.BoardMapper.saveUpdateBoard", board);
	}


	public List<Board> getBoardList2(
			   @Param("id") String id,
		        @Param("payOption") String payOption,
		        @Param("userRank") String userRank,
		        @Param("searchType") String searchType,
		        @Param("searchKeyword") String searchKeyword,
		        @Param("startDate") String startDate,
		        @Param("endDate") String endDate,
		        @Param("delegateUserRank") String delegateUserRank,
		        @Param("grantStartTime") Date grantStartTime,
		        @Param("grantEndTime") Date grantEndTime,
		        @Param("delegateapproverid") String delegateapproverid
		        
		) {

		    Map<String, Object> paramMap = new HashMap<>();
		    paramMap.put("id", id);
		    paramMap.put("userRank", userRank);
		    paramMap.put("payOption", payOption);
		    paramMap.put("searchType", searchType);
		    paramMap.put("searchKeyword", searchKeyword);
		    paramMap.put("startDate", startDate);
		    paramMap.put("endDate", endDate);
		    paramMap.put("delegateUserRank", delegateUserRank);
		    paramMap.put("grantStartTime", grantStartTime);
		    paramMap.put("grantEndTime", grantEndTime);
		    paramMap.put("delegateapproverid", delegateapproverid);
		    System.out.println(payOption + "검색옵션확인");
		    System.out.println(userRank + "rank확인");

		    return sqlSession.selectList("mapper.BoardMapper.getBoardList", paramMap);
	}

	

	public List<Board> getBoardList(
	        @Param("id") String id,
	        @Param("payOption") String payOption,
	        @Param("userRank") String userRank,
	        @Param("searchType") String searchType,
	        @Param("searchKeyword") String searchKeyword,
	        @Param("startDate") String startDate,
	        @Param("endDate") String endDate
	) {

	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("id", id);
	    paramMap.put("userRank", userRank);
	    paramMap.put("payOption", payOption);
	    paramMap.put("searchType", searchType);
	    paramMap.put("searchKeyword", searchKeyword);
	    paramMap.put("startDate", startDate);
	    paramMap.put("endDate", endDate);
	    System.out.println(payOption + "검색옵션확인");
	    System.out.println(userRank + "rank확인");

	    return sqlSession.selectList("mapper.BoardMapper.getBoardList", paramMap);
	}



	public Board getBoardBySeq(int seq) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.BoardMapper.getBoardBySeq", seq);
	}


	public String getUserNameById(String checkName) {
		System.out.println(checkName+"결재자 아이디 확인");
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.BoardMapper.getUserNameById", checkName);
	}


	public void submitHistory(String id, int PostSeq) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("id", id);
	    params.put("postSeq", PostSeq);
	    sqlSession.insert("mapper.BoardMapper.insertHistory", params);
	}
	
	public void submitHistoryD(String id, int postSeq, String delegateApproverId, String delegateApproverRank) {
		// TODO Auto-generated method stub
		  Map<String, Object> params = new HashMap<>();
		    params.put("id", id);
		    params.put("postSeq", postSeq);
		    params.put("delegateApproverId", delegateApproverId);
		    params.put("delegateApproverRank", delegateApproverRank);

		    
		    sqlSession.insert("mapper.BoardMapper.insertHistoryD", params);
	}



	public void submitSaveHistory(String id, int postSeq) {
		// TODO Auto-generated method stub
		  Map<String, Object> params = new HashMap<>();
		    params.put("id", id);
		    params.put("postSeq", postSeq);
		    sqlSession.insert("mapper.BoardMapper.submitSaveHistory", params);
	}


		public List<History> getHistoryListByBoardSeq(int seq) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.BoardMapper.getHistoryListByBoardSeq", seq);
	}


		public void submitCheckHistory(String loginId, int postSeq, String payOption) {
			   Map<String, Object> params = new HashMap<>();
			    params.put("id", loginId);
			    params.put("postSeq", postSeq);
			    params.put("payOption", payOption);
			    sqlSession.insert("mapper.BoardMapper.submitCheckHistory", params);
		}


		public List<String> getDelegatesByRank(String loggedInUserRank) {
		    Map<String, Object> parameterMap = new HashMap<>();
		    parameterMap.put("loggedInUserRank", loggedInUserRank);

		    return sqlSession.selectList("mapper.BoardMapper.getDelegatesByRank", parameterMap);
		}


		public List<Map<String, Object>> getDelegateInfoByIds(List<String> delegateIds) {
		    Map<String, Object> parameters = new HashMap<>();
		    parameters.put("delegateIds", delegateIds);
		    return sqlSession.selectList("mapper.BoardMapper.getDelegateInfoByIds", parameters);
		}


		public String getDelegateRankByIds(String delegateId) {
			// TODO Auto-generated method stub
			 Map<String, Object> parameters = new HashMap<>();
			    parameters.put("delegateId", delegateId);
			 return sqlSession.selectOne("mapper.BoardMapper.getDelegateRankByIds", parameters);
		}


		public int approveDelegate(String loggedInUserId, String delegateId) {
			// TODO Auto-generated method stub
			 Map<String, Object> parameters = new HashMap<>();
			    parameters.put("loggedInUserId", loggedInUserId);
			    parameters.put("delegateId", delegateId);
			    sqlSession.delete("mapper.BoardMapper.deleteDelegate", parameters);
			 return sqlSession.insert("mapper.BoardMapper.approveDelegate", parameters);
		}


		public Delegate getDelegatecheckInfo(String id) {
			// TODO Auto-generated method stub
			 Map<String, Object> parameters = new HashMap<>();
			    parameters.put("id", id);
			return sqlSession.selectOne("mapper.BoardMapper.getDelegatecheckInfo", parameters);
		}


	


	
	


	




		


	


	





	

}
