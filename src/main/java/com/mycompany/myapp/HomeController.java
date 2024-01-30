package com.mycompany.myapp;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nexacro.java.xapi.data.DataSet;
import com.nexacro.java.xapi.data.DataTypes;
import com.nexacro.java.xapi.data.PlatformData;
import com.nexacro.java.xapi.data.Variable;
import com.nexacro.java.xapi.tx.HttpPlatformRequest;
import com.nexacro.java.xapi.tx.HttpPlatformResponse;
import com.nexacro.java.xapi.tx.PlatformException;
import com.nexacro.java.xapi.tx.PlatformType;

import service.PostService; // �ùٸ� ��Ű�� ��θ� ���
import util.BoardStatusConverter;
import model.Board;
import model.Delegate;
import model.History;
import model.User;
import model.Delegate;


@Controller
public class HomeController {
	
	@Autowired
	 private SqlSession sql; // SqlSession�� �����մϴ�.
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private PostService postService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		
		return "home";
	}
	


			//search
			 @RequestMapping(value = "/search", method = RequestMethod.GET)
			 public String search(@RequestParam("id") String id,
			                      @RequestParam("searchType") String searchType,
			                      @RequestParam("searchKeyword") String searchKeyword,
			                      @RequestParam("approveStatus") String payOption,
			                      @RequestParam("startDate") String startDate,
			                      @RequestParam("endDate") String endDate,
			                      HttpSession session,
			                      Model model) {
				 System.out.println("@@@@@@@@@@@@@@@@@@");
			     // �˻� ���� ���� ����
				 User user = postService.getUserInfo(id);
				   String userRank = user.getRank();
				   System.out.println(userRank+"�� �������� �˻�");
			        List<Board> boardList = new ArrayList<>(); // Initialize with an empty list
			            // �ٸ� ����� �Խ��� ����� ������ (��: �Ϲ� ����)
			            boardList = postService.getBoardList(id, payOption, userRank, searchType, searchKeyword, startDate, endDate);
			            model.addAttribute("boardList", boardList);
			            System.out.println(boardList+"�������� �׸� Ȯ��");
			     return "mainList";
			 }
			
			 //���� ȭ�� �������� 
			 @RequestMapping(value = "/boardList", method = RequestMethod.POST)
			 public String boardList(@RequestParam("id") String id,
					 Model model, HttpSession session) {
				   // �α��� ���� �� ������� �̸��� ������ ������
				    User user = postService.getUserInfo(id);
				 
				  List<Board> boardList = new ArrayList<>(); // Initialize with an empty list
				   String payOption = null;
			        String searchType = null;
			        String searchKeyword = null;
			        String userRank = user.getRank();
			        String startDate = null;
			        String endDate = null;
			        // delegate�� null�� �ƴ� ��� �߰����� ���� ����
			        
			        
			        
				  // �ٸ� ����� �Խ��� ����� ������ (��: �Ϲ� ����)
		            boardList = postService.getBoardList(id, payOption, userRank, searchType, searchKeyword, startDate, endDate);
	
		        model.addAttribute("boardList", boardList);
		        System.out.println(boardList + "���������� üũ ���帮��Ʈ");
				 
				 return "redirect:/mainList";
				
			 }
			 
			 @RequestMapping(value = "/delegateList", method = RequestMethod.GET)
			 @ResponseBody
			 public List<Map<String, Object>> getDelegateList(@RequestParam("loggedInUserRank") String loggedInUserRank) {
			     // ���ǿ��� �α����� ������� rank�� �����ɴϴ�. (���� ������ ��� ����� �����ӿ�ũ�� ������ ���� �ٸ� �� �ֽ��ϴ�.)
			     System.out.println(loggedInUserRank + "��ũ���� Ȯ��");

			     // �븮�� ����� �������� ������ �����ؾ� �մϴ�.
			     List<String> delegateList = postService.getDelegatesByRank(loggedInUserRank);
			     System.out.println(delegateList + "���̵� ã�� ������ ����� ��� ��Ȯ��");

			     // �븮�� ����� id��� �̸��� rank�� ��ȸ
			     List<Map<String, Object>> delegateInfoList = postService.getDelegateInfoByIds(delegateList);
			     System.out.println(delegateInfoList + "����ã�� ");
			     
			     // delegateInfoList�� ��ȯ
			     return delegateInfoList;
			 }
			 
			 @RequestMapping(value = "/getDelegateRank", method = RequestMethod.GET)
			 @ResponseBody
			 public Map<String, Object> getDelegateRank(@RequestParam("delegateId") String delegateId) {
			     Map<String, Object> result = new HashMap<>();
			     System.out.println(delegateId + "�޾ƿ� ���̵� Ȯ��");
			     String delegateRank = postService.getDelegateRankByIds(delegateId);
			     System.out.println(delegateRank + "������ ���� ���");
			     result.put("rank", delegateRank);
			     return result;
			 }
			 
			 @RequestMapping(value = "/approveDelegate", method = RequestMethod.GET)
			 @ResponseBody
			 public String approveDelegate(
			         @RequestParam("loggedInUserId") String loggedInUserId,
			         @RequestParam("delegateId") String delegateId) {
			     
			     // Perform your approval logic in the service layer
			     int approvalResult = postService.approveDelegate(loggedInUserId, delegateId);

			     if (approvalResult >= 1) {
			         return "success"; // Adjust the response as needed
			     } else {
			         return "fail"; // Adjust the response as needed
			     }
			 }



			 
		
				@RequestMapping(value = "/practice", method = RequestMethod.GET)
				public String practice() {
					return "practice"; // "payLogin.jsp"�� �̵�
				}
				
				@RequestMapping(value = "/instead", method = RequestMethod.GET)
				public String instead() {
					return "instead"; // 
				}

				//�α��� ������ �̵�
				@RequestMapping(value = "/payLogin", method = RequestMethod.GET)
				public String payLogin() {
					System.out.println("���⼭ �Ǵ°ǰ�? Ȩ��Ʋ�ѷ� ȣ��");
					return "payLogin"; // "payLogin.jsp"�� �̵�
				}
			 
				// �α��� ó��
				@RequestMapping(value = "/checkId", method = RequestMethod.POST)
				public String checkId(@RequestParam("id") String id,
				                      @RequestParam("pwd") String pwd,
				                      Model model,
				                      HttpSession session) {

				    // Service�� ���� ID�� ��й�ȣ Ȯ��
				    int userIdCount = postService.findId(id);
				    int passwordCount = postService.checkPassword(id, pwd);

				    // ��ϵ��� ���� ������� ���
				    if (userIdCount == 0) {
				        model.addAttribute("error", "��ϵ��� ���� ������Դϴ�.");
				        return "redirect:/payLogin"; // payLogin �������� �����̷�Ʈ
				    } else if (passwordCount == 0) {
				        model.addAttribute("error", "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				        return "redirect:/payLogin"; // payLogin �������� �����̷�Ʈ
				    }

				    // �α��� ���� �� ������� �̸��� ������ ������
				    User user = postService.getUserInfo(id);

				    if (user != null) {
				        // ����� ������ �ִٸ� ���ǿ� ����
				        session.setAttribute("loggedInUserId", id);
				        session.setAttribute("loggedInUserName", user.getMemName());
				        session.setAttribute("loggedInUserRank", user.getRank());
				    }

				    return "redirect:/mainList"; 
				}
			 
				
				// ���� ������ �̵�
				@RequestMapping(value = "/mainList", method = RequestMethod.GET)
				public String mainList(HttpServletRequest request,
						
						Model model) {

				    // ���ǿ��� id ���� ������
				    String id = (String) request.getSession().getAttribute("loggedInUserId");

				    // ���ǿ� id�� ������ �α��� �������� �����̷�Ʈ
				    if (id == null) {
				        return "redirect:/payLogin";
				    }
				    
				    User user = postService.getUserInfo(id);
				    Delegate delegate = postService.getDelegateInfo(id);
				    System.out.println(delegate+"delegateȮ��@@");
				    String payOption = null;
				    String searchType = null;
				    String searchKeyword = null;
				    String userRank = user.getRank();
				    String startDate = null;
				    String endDate = null;
				    List<Board> boardList = new ArrayList<>(); // Initialize with an empty list
				    
				    if (delegate != null) {
				        // delegate�� null�� �ƴ� ���
				        String delegateapproverid = delegate.getDelegateApproverId();
				        Date grantStartTime = delegate.getGrantStartTime();
				        Date grantEndTime = delegate.getGrantEndTime();
				        String delegateUserRank = delegate.getDelegateApproverRank();
				        String delegateUserName = delegate.getDelegateApproverName();

				        // ���⿡ ���ϴ� �۾� ����
				        model.addAttribute("delegateapproverid", delegateapproverid); // delegateapproverid �߰�
				        model.addAttribute("delegateUserRank", delegateUserRank);
				        model.addAttribute("delegateUserName", delegateUserName);
				        model.addAttribute("delegateStartTime", grantStartTime);
				        model.addAttribute("delegateEndTime", grantEndTime);

				        // ������ boardList �ʱ�ȭ �� ���ο� boardList ��������
				        boardList = postService.getBoardList2(id, payOption, userRank, searchType, searchKeyword, startDate, endDate, delegateUserRank, grantStartTime, grantEndTime, delegateapproverid);
				        System.out.println(searchType + "�˻� Ÿ�� Ȯ��1");
				    } else {
				        // delegate�� null�� ���
				        // �ٸ� ����� �Խ��� ����� ������ (��: �Ϲ� ����)
				        boardList = postService.getBoardList(id, payOption, userRank, searchType, searchKeyword, startDate, endDate);
				        System.out.println(searchType + "�˻� Ÿ�� Ȯ��2");
				    }

				   
				    System.out.println(id + userRank + "rank���� ������ Ȯ��");
				    model.addAttribute("boardList", boardList);
				    System.out.println(boardList + "���������� üũ ���帮��Ʈ");

				    return "mainList";
				}


			 
			 
			 
			 
			
			//update AJAX
			@RequestMapping(value = "/searchoption", method = RequestMethod.GET)
			@ResponseBody
			public List<Board> searchoption(
			    @RequestParam("id") String id,
			    @RequestParam("approveStatus") String payOption,
			    @RequestParam(value = "searchType", required = false) String searchType, // Added searchType parameter
			    @RequestParam(value = "searchKeyword", required = false) String searchKeyword, // Added searchKeyword parameter
			    @RequestParam("startDate") String startDate,
                @RequestParam("endDate") String endDate,
			    HttpSession session,
			    Model model) {
			    System.out.println("�˻��Ϸ� ��Ʈ�ѷ� ��");
			    User user = postService.getUserInfo(id);
			    String userRank = user.getRank();

			    // You can use searchType and searchKeyword as needed

			    List<Board> boardList = postService.getBoardList(id, payOption, userRank, searchType, searchKeyword, startDate, endDate);
			    
			    // ������¸� �ѱ۷� ��ȯ�Ͽ� �����Ϳ� �߰�
			    for (Board board : boardList) {
			        board.setPayOption(BoardStatusConverter.getKoreanStatus(board.getPayOption()));
			    }

	
			    System.out.println(boardList);
			    return boardList;
			}
			
			//��������
			@RequestMapping(value = "/detail", method = RequestMethod.GET)
			public String detail(@RequestParam("seq") int seq,
					@RequestParam("userId") String userId,
					@RequestParam("userRank") String userRank,
					Model model) {
			    // �Խñ� ���� ��ȸ
			    Board board = postService.getBoardBySeq(seq);
			    Delegate delegate = postService.getDelegateInfo(userId);
			    if (delegate != null) {
		            // ���⿡ delegate�� null�� �ƴ� �� ������ �߰����� ���� �ڵ� �ۼ�
		            // ��: delegate�� ���õ� ������ �𵨿� �߰��Ͽ� �信�� ����� �� �ֵ��� ��
		            model.addAttribute("delegateInfo", delegate);
		            System.out.println(delegate.getDelegateApproverRank()+"������ ���� ������@@@?");
		        }
			    
			    
			    // �����丮 ���� ��ȸ (����, ���� ������ ��ȸ �ڵ忡 �°� ���� �ʿ�)
			    List<History> historyList = postService.getHistoryListByBoardSeq(seq);
			    
			    String id = userId;
			    User user = postService.getUserInfo(id);

			    
			    // �𵨿� �Խñ� �� �����丮 ���� �߰�
			    model.addAttribute("board", board);
			    model.addAttribute("historyList", historyList);
			    model.addAttribute("user", user);

			    // �� �̸� ��ȯ
			    return "detail";
			}
			

			//logOut
			@RequestMapping(value = "/logout", method = RequestMethod.POST)
			public String logout(HttpSession session) {
			    // Invalidate the session to log the user out
			    session.invalidate();
			    // Redirect to the login page or any other desired page
			    return "redirect:/payLogin";
			}
			
			
			@RequestMapping(value = "/payWriteForm", method = RequestMethod.GET)
			public String payWriteForm(Model model,
					HttpSession session,
			        @RequestParam("id") String loggedInUserId,
			        @RequestParam("userName") String loggedInUserName,
			        @RequestParam("userRank") String loggedInUserRank) {

			    if (loggedInUserId != null) {
			    	   int seqNum = postService.findSeq();
			    	    model.addAttribute("seqNum", seqNum);
			    		System.out.println(seqNum);
						model.addAttribute("seqNum", seqNum);
						System.out.println("Model: " + model.asMap());

			    }else {
			    	  return "redirect:/payLogin";
			    }

			    // �۾��� �������� �̵�
			    return "payWriteForm";
			}
			
			@RequestMapping(value = "/updateBoard", method = RequestMethod.POST)
			public String updateBoard(
			        @RequestParam("seq") int seq,
			        @RequestParam("id") String id,
			        @RequestParam("payOption") String payOption,
			        @RequestParam("subject") String subject,
			        @RequestParam("content") String content,
			        @RequestParam("action") String action,
			        @RequestParam("loginId") String loginId,
			        HttpSession session,
			        Model model) {

			    System.out.println("�Ѿ������ Ȯ��2");
			    System.out.println(action + loginId + seq + content + payOption + "�α��η�ũȮ�� �����Ҷ�");

			    User user = postService.getUserInfo(loginId);
			    Delegate delegate = postService.getDelegateInfo(loginId);
			    
			    // delegate�� null�� ��� �⺻���� �����ϰų� ó���� ������ �� �ֽ��ϴ�.
			    if (delegate == null) {
			        // �⺻�� ���� �Ǵ� Ư���� ó���� ������ �� ����
			        delegate = new Delegate();
			        
			    }
			    
			    String delegateRank = delegate.getDelegateApproverRank();
			    String ApproverId = delegate.getDelegateApproverId();
			
			    String userRank = user.getRank();

			    switch (action) {
			        case "save":
			            postService.saveUpdateBoard(seq, id, subject, content);
			            postService.submitSaveHistory(id, seq);
			            break;

			        case "approval":
			            handleApprovalAction(userRank, delegateRank, ApproverId, seq, id, subject, content, payOption, loginId);
			            break;

			        case "reject":
			            postService.rejectBoardseq(seq, loginId);
			            postService.submitCheckHistory(loginId, seq, "reject");
			            break;

			        default:
			            System.out.println("����Ʈ��");
			            break;
			    }

			    return "redirect:/mainList";
			}
				
			private void handleApprovalAction(String userRank, String delegateRank, String ApproverId, int seq, String id, String subject, String content, String payOption, String loginId) {
			    if ("����".equals(userRank) || "����".equals(delegateRank)) {
			        if ("save".equals(payOption)) {
			            payOption = "checking";
			            postService.checkBoard(seq, id, subject, content, payOption, loginId);
			            postService.submitCheckHistory(loginId, seq, payOption);
			        } else {
			            if ("����".equals(delegateRank)) {
			                postService.checkBoardD(seq, id, subject, content, payOption, loginId, ApproverId);
			                postService.submitCheckHistory(loginId, seq, payOption);
			                //���⿡�� submit ���� �����丮 �����Ҷ� delegate�� ���������� delegate rank �� delegate id�� �߰��� �������� ����
			                
			                
			            } else {
			                postService.checkBoard(seq, id, subject, content, payOption, loginId);
			                postService.submitCheckHistory(loginId, seq, payOption);
			            }
			        }
			    } else if ("����".equals(userRank) || "����".equals(delegateRank)) {
			        if ("save".equals(payOption)) {
			            payOption = "wait";
			            postService.checkBoard(seq, id, subject, content, payOption, loginId);
			            postService.submitCheckHistory(loginId, seq, payOption);
			        } else {
			            if ("����".equals(delegateRank)) {
			                postService.checkBoardD(seq, id, subject, content, payOption, loginId, ApproverId);
			                postService.submitCheckHistory(loginId, seq, payOption);
			                //���⿡�� submit ���� �����丮 �����Ҷ� delegate�� ���������� delegate rank �� delegate id�� �߰��� �������� ����
			            } else if ("����".equals(userRank)) {
			                postService.checkBoard(seq, id, subject, content, payOption, loginId);
			                postService.submitCheckHistory(loginId, seq, payOption);
			            }
			        }
			    } else {
			        postService.updateBoard(seq, id, subject, content);
			        postService.submitHistory(id, seq);
			    }
			}


					
			//submit
			@RequestMapping(value = "/submitWriteForm", method = RequestMethod.POST)
			public String submitWriteForm(
			        @RequestParam("id") String id,
			        @RequestParam("subject") String subject,
			        @RequestParam("content") String content,
			        @RequestParam("payOption") String payOption, // Add this line for payOption
			        HttpSession session,
			        Model model) {
			    System.out.println("�Ѿ������ Ȯ��22");
			    if ("save".equals(payOption)) {
			        // Your logic for handling 'save' option goes here
			    	 int PostSeq = postService.savesubmitWriteForm(id, subject, content);
			    	 postService.submitSaveHistory(id, PostSeq);
			    }
			    else {
			    	
			     	Delegate delegate = postService.getDelegateInfo(id);
			        User user = postService.getUserInfo(id);
				    String userRank = user.getRank();
			    	 if (delegate != null) {
				            // ���⿡ delegate�� null�� �ƴ� �� ������ �߰����� ���� �ڵ� �ۼ�
			    		 String DelegateApproverId = delegate.getDelegateApproverId();
			    		 String DelegateApproverRank = delegate.getDelegateApproverRank();
			    	 	 // postService.submitWriteForm �޼��忡 �ʿ��� ���ڵ��� �����Ͽ� ȣ��
					    	int PostSeq = postService.submitWriteFormD(id, subject, content, userRank, DelegateApproverRank, DelegateApproverId);
					    	System.out.println(DelegateApproverRank+"�����丮 ������ ��ũȮ��@@");
					    	postService.submitHistoryD(id, PostSeq, DelegateApproverId, DelegateApproverRank);
				        }else {
				        	 // postService.submitWriteForm �޼��忡 �ʿ��� ���ڵ��� �����Ͽ� ȣ��
					    	int PostSeq = postService.submitWriteForm(id, subject, content, userRank);
						    postService.submitHistory(id, PostSeq);
				        }
			    	
			    	
			   

			    }

			
			    // Do something with the payOption parameter (e.g., save it to the database)

			    // �α��� ���� �� ������� �̸��� ������ ������
			    User user = postService.getUserInfo(id);
			    String userRank = user.getRank();
			    if (user != null) {
			        // ����� ������ �ִٸ� ���ǿ� ����
			        session.setAttribute("loggedInUserId", id);
			        session.setAttribute("loggedInUserName", user.getMemName());
			        session.setAttribute("loggedInUserRank", user.getRank());
			        
			        String searchType = null;
			        String searchKeyword = null;
			        		payOption = null;
			        String startDate = null;
			        String endDate = null;
			        List<Board> boardList = postService.getBoardList(id, payOption, userRank, searchType, searchKeyword, startDate, endDate);
			        model.addAttribute("boardList", boardList);
			        System.out.println(boardList + "���������� üũ ���帮��Ʈ");
			    }

			    return "redirect:/mainList"; // ������ URL�� ����
			}


			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			@RequestMapping("nexaConn")
			public void nexaList(HttpServletRequest request, HttpServletResponse response) throws PlatformException {
			    System.out.println("================nexa start================");

			    HttpPlatformRequest req = new HttpPlatformRequest(request);
			    req.receiveData();
			    PlatformData inData = req.getData();
			    Variable keyword = inData.getVariable("keyword");
			    String keywordValue = keyword.getString(); // Ű���� �� ��������
			    
			    // searchType �� ��������
			    Variable searchType = inData.getVariable("searchType");
			    String searchTypeValue = searchType.getString();

			    // ���Ŀ� keywordValue�� ����Ͽ� �ʿ��� ���� ����
			    
			    Map<String, Object> paramMap = new HashMap<>();
		      //  paramMap.put("searchType", searchType);
		        paramMap.put("keyword", keywordValue);
		        paramMap.put("searchtype", searchTypeValue);

			    System.out.println(keywordValue + "�޾ƿ� �Ķ���Ͱ� Ȯ��");
			    
			    if (sql != null) {
			        List<Map<String, Object>> list = sql.selectList("mapper.BoardMapper.boardList", paramMap);

			        if (list != null) {
			        	 DataSet ds = new DataSet("board");
			            ds.addColumn("seq", DataTypes.INT, 10);
			            ds.addColumn("mem_name", DataTypes.STRING, 255);
			            ds.addColumn("mem_id", DataTypes.STRING, 255);
			            ds.addColumn("board_subject", DataTypes.STRING, 255);
			            ds.addColumn("board_content", DataTypes.STRING, 4000);

			            System.out.println("ds������ ������ �� Ȯ��" + ds);

			            for (int i = 0; i < list.size(); i++) {
			                int row = ds.newRow();
			                ds.set(row, "seq", list.get(i).get("seq"));
			                ds.set(row, "mem_name", list.get(i).get("mem_name"));
			                ds.set(row, "mem_id", list.get(i).get("mem_id"));
			                ds.set(row, "board_subject", list.get(i).get("board_subject"));
			                ds.set(row, "board_content", list.get(i).get("board_content"));
			            }

			            PlatformData outData = new PlatformData();
			            outData.addDataSet(ds);

			            HttpPlatformResponse res = new HttpPlatformResponse(response, PlatformType.CONTENT_TYPE_XML, "UTF-8");
			            res.setData(outData);
			            res.sendData(); //���⿡�� �ѱ�
			            System.out.println(keywordValue+"�޾ƿ� �Ķ���Ͱ� Ȯ��");
			            System.out.println(searchTypeValue + "�޾ƿ� searchType �Ķ���Ͱ� Ȯ��");
			            System.out.println("================nexa end================");
			        } else {
			            System.out.println("List is null");
			        }
			    } else {
			        System.out.println("sql object is null");
			    }
			}


}
