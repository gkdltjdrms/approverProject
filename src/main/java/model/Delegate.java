package model;

import java.sql.Timestamp;

public class Delegate {

    private long seq;
    private String delegateApproverId;
    private Timestamp grantStartTime;
    private Timestamp grantEndTime;
    private String delegateId;
    private String delegateApproverRank;
    private String delegateApproverName;

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public String getDelegateApproverId() {
        return delegateApproverId;
    }

    public void setDelegateApproverId(String delegateApproverId) {
        this.delegateApproverId = delegateApproverId;
    }

    public Timestamp getGrantStartTime() {
        return grantStartTime;
    }

    public void setGrantStartTime(Timestamp grantStartTime) {
        this.grantStartTime = grantStartTime;
    }

    public Timestamp getGrantEndTime() {
        return grantEndTime;
    }

    public void setGrantEndTime(Timestamp grantEndTime) {
        this.grantEndTime = grantEndTime;
    }

    public String getDelegateId() {
        return delegateId;
    }

    public void setDelegateId(String delegateId) {
        this.delegateId = delegateId;
    }

    public String getDelegateApproverRank() {
        return delegateApproverRank;
    }

    public void setDelegateApproverRank(String delegateApproverRank) {
        this.delegateApproverRank = delegateApproverRank;
    }

    public String getDelegateApproverName() {
        return delegateApproverName;
    }

    public void setDelegateApproverName(String delegateApproverName) {
        this.delegateApproverName = delegateApproverName;
    }
}
