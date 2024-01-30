package model;

import java.util.Date;
import java.util.List;

public class BoardWrite {
    private int seq;
    private String mem_name;
    private String mem_id;
    private String board_subject;
    private String board_content;
    private Date reg_date;
    private Date upt_date;
    private int view_cnt;

    // Getter와 Setter 메서드

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getMem_name() {
        return mem_name;
    }

    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }

    public String getMem_id() {
        return mem_id;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public String getBoard_subject() {
        return board_subject;
    }

    public void setBoard_subject(String board_subject) {
        this.board_subject = board_subject;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public Date getUpt_date() {
        return upt_date;
    }

    public void setUpt_date(Date upt_date) {
        this.upt_date = upt_date;
    }

    public int getView_cnt() {
        return view_cnt;
    }

    public void setView_cnt(int view_cnt) {
        this.view_cnt = view_cnt;
    }

	public void setFileNames(List<String> fileNames) {
		// TODO Auto-generated method stub
		
	}
}
