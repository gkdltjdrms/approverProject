package model;

public class SearchCriteria {
    private int page;
    private int pageSize;
    private String keyword;
    private String searchOption;
    private String startDate;
    private String endDate;

    // ������, getter, setter �� �ʿ��� �޼��� �߰�

    // �⺻ ������
    public SearchCriteria() {
        this.page = 1;
        this.pageSize = 10; // �⺻������ �����ϰų� �ʿ信 ���� �ٸ��� ����
    }

    // ������
    public SearchCriteria(int page, int pageSize, String keyword, String searchOption, String startDate, String endDate) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.searchOption = searchOption;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getter�� Setter �޼��� �߰�
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getOffset() {
        return (page - 1) * pageSize;
    }

    public int getStartRow() {
        return getOffset() + 1;
    }

    public int getEndRow() {
        return getOffset() + pageSize;
    }

    public String getSearchOption() {
        return searchOption;
    }

    public void setSearchOption(String searchOption) {
        this.searchOption = searchOption;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
