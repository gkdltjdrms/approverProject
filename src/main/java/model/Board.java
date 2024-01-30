package model;

import java.util.Date;

public class Board {
	private String memName;
    private int seq;
    private Date regDate;
    private String id;
    private String subject;
    private String content;
    private Date checkDate;
    private String payOption;
    private String checkName;
    private String history;
    private String checkBoardName; // Add this line
    private String delegateRank;
    private String delegateId;
    private Date grantstarttime;
    private Date grantendtime;
    private String delegateName;

    
	// 게터와 세터
    public String getCheckBoardName() {
        return checkBoardName;
    }

    public void setCheckBoardName(String checkBoardName) {
        this.checkBoardName = checkBoardName;
    }
    
    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }


    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getPayOption() {
        return payOption;
    }

    public void setPayOption(String payOption) {
        this.payOption = payOption;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

	public String getDelegateRank() {
		return delegateRank;
	}

	public void setDelegateRank(String delegateRank) {
		this.delegateRank = delegateRank;
	}

	public String getDelegateId() {
		return delegateId;
	}

	public void setDelegateId(String delegateId) {
		this.delegateId = delegateId;
	}

	public Date getGrantstarttime() {
		return grantstarttime;
	}

	public void setGrantstarttime(Date grantstarttime) {
		this.grantstarttime = grantstarttime;
	}

	public Date getGrantendtime() {
		return grantendtime;
	}

	public void setGrantendtime(Date grantendtime) {
		this.grantendtime = grantendtime;
	}


	public String getDelegateName() {
		return delegateName;
	}

	public void setDelegateName(String delegateName) {
		this.delegateName = delegateName;
	}
}
