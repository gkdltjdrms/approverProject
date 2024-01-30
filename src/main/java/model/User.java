package model;

import java.util.Date;

public class User {
    private String id;
    private String pwd;
    private String memName;
    private String rank;
    private String delegateapproverid;
    private Date grantstarttime;
    private Date grantendtime;

    // 생성자, getter, setter 등은 필요에 따라 추가할 수 있습니다.

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

	public String getDelegateapproverid() {
		return delegateapproverid;
	}

	public void setDelegateapproverid(String delegateapproverid) {
		this.delegateapproverid = delegateapproverid;
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
}
