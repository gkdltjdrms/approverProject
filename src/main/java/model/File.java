package model;

public class File {
    private int fileSeq;
    private int listSeq;
    private String saveName;
    private String savePath;
    private String realNames;
    
    public String getRealName() {
        return realNames;
    }

    public void setRealName(String realNames) {
        this.realNames = realNames;
    }

    public int getFileSeq() {
        return fileSeq;
    }

    public void setFileSeq(int fileSeq) {
        this.fileSeq = fileSeq;
    }

    public int getListSeq() {
        return listSeq;
    }

    public void setListSeq(int listSeq) {
        this.listSeq = listSeq;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
}
