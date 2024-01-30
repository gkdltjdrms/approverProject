package model;

public class SearchCriteria {
    private int page;
    private int pageSize;
    private String keyword;
    private String searchOption;
    private String startDate;
    private String endDate;

    // 생성자, getter, setter 등 필요한 메서드 추가

    // 기본 생성자
    public SearchCriteria() {
        this.page = 1;
        this.pageSize = 10; // 기본값으로 설정하거나 필요에 따라 다르게 설정
    }

    // 생성자
    public SearchCriteria(int page, int pageSize, String keyword, String searchOption, String startDate, String endDate) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.searchOption = searchOption;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getter와 Setter 메서드 추가
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
