package cn.com.scal.components.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vslimit on 15/9/29.
 */
public class Pagination {
    private Integer currentPage = 1;
    private Integer nextPageNumber;
    private Integer previousPageNumber;
    private Integer lastPageNumber;
    private Integer pageSize = 10;
    private Integer count = 0;

    private Integer after_range_num = 4;
    private Integer before_range_num = 3;
    private static final String COUNT = "-count";
    private static final String CURRENT_PAGE_TAG = "currentPage";
    private static final String EQUAL_TAG = "=";
    private static final String QUESTION_TAG = "?";
    private static final String LINK_TAG = "&";
    private List<Integer> pageNumList = new ArrayList<Integer>();


    public Integer getDataCount() {
        return count;
    }

    private Integer getBegin() {
        return (currentPage - 1) * pageSize;
    }

    private Integer getEnd() {
        return currentPage * pageSize >= count ? count : currentPage * pageSize;
    }


    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getLastPageNumber() {
        lastPageNumber = (getDataCount() - 1) / pageSize + 1;
        return lastPageNumber;
    }


    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage <= 0 ? 1 : currentPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getNextPageNumber() {
        if (currentPage >= 1 && currentPage < getLastPageNumber()) {
            nextPageNumber = currentPage + 1;
        } else {
            nextPageNumber = getLastPageNumber();
        }
        return nextPageNumber;
    }

    public Integer getPreviousPageNumber() {
        if (currentPage > 1 && currentPage <= getLastPageNumber()) {
            previousPageNumber = currentPage - 1;
        } else {
            previousPageNumber = 1;
        }
        return previousPageNumber;
    }

    public List<Integer> getPageNumList() {
        int to = currentPage + before_range_num > getLastPageNumber() ? getLastPageNumber() : currentPage + before_range_num;
        if (currentPage >= after_range_num) {
            for (int i = currentPage - after_range_num > 0 ? currentPage - after_range_num : 1; i <= to; i++) {
                pageNumList.add(i);
            }
        } else {
            for (int i = 1; i <= to; i++) {
                pageNumList.add(i);
            }
        }
        return pageNumList;
    }

    public String generateUrl(Integer currPage) {
        if (StringUtil.isEmpty(pageUrl)) {
            return CURRENT_PAGE_TAG + EQUAL_TAG + String.valueOf(currPage);
        } else {
            if (pageUrl.contains(CURRENT_PAGE_TAG)) {
                pageUrl = pageUrl.replaceAll(CURRENT_PAGE_TAG + EQUAL_TAG + "[^&]*&?", CURRENT_PAGE_TAG + EQUAL_TAG + String.valueOf(currPage) + LINK_TAG);
                return pageUrl;
            } else {
                return pageUrl + LINK_TAG + CURRENT_PAGE_TAG + EQUAL_TAG + String.valueOf(currPage)  ;
            }
        }
    }

    private String pageUrl;

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
