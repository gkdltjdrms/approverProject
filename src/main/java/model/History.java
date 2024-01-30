package model;

import java.util.Date;

public class History {
    private int seq;
    private int historyNum;  // 추가: 히스토리 번호
    private Date check_Date;
    private String checkName;
    private String payOption;
    private int boardList;
    private String checkMemName;
    private String delegatecheckMemName;

    // Constructors, getters, and setters

    public History() {
        // Default constructor
    }

    public History(int seq, int historyNum, Date checkDate, String checkName, String payOption, int boardList) {
        this.seq = seq;
        this.historyNum = historyNum;
        this.check_Date = checkDate;
        this.checkName = checkName;
        this.payOption = payOption;
        this.boardList = boardList;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getHistoryNum() {
        return historyNum;
    }

    public void setHistoryNum(int historyNum) {
        this.historyNum = historyNum;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getPayOption() {
        return payOption;
    }

    public void setPayOption(String payOption) {
        this.payOption = payOption;
    }

    public int getBoardList() {
        return boardList;
    }

    public void setBoardList(int boardList) {
        this.boardList = boardList;
    }

    @Override
    public String toString() {
        return "History{" +
                "seq=" + seq +
                ", historyNum=" + historyNum +
                ", checkDate=" + check_Date +
                ", checkName='" + checkName + '\'' +
                ", payOption='" + payOption + '\'' +
                ", boardList=" + boardList +
                '}';
    }

    public String getCheckMemName() {
        return checkMemName;
    }

    public void setCheckMemName(String checkMemName) {
        this.checkMemName = checkMemName;
    }

    public Date getCheck_Date() {
        return check_Date;
    }

    public void setCheck_Date(Date check_Date) {
        this.check_Date = check_Date;
    }

	

	public String getDelegatecheckMemName() {
		return delegatecheckMemName;
	}

	public void setDelegatecheckMemName(String delegatecheckMemName) {
		this.delegatecheckMemName = delegatecheckMemName;
	}
}
