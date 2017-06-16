
package com.tc.pes.web.serialize;


public class QueryConditionSerializeObject extends JsonSerializeObject {

    private final Integer DEFULT_PAGEINDEX = 1;

    private final Integer DEFULT_PAGESIZE = 10;

    protected Integer pageIndex = DEFULT_PAGEINDEX;

    protected Integer pageSize = DEFULT_PAGESIZE;

    public Integer getPageIndex() {

        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {

        if (pageIndex == null)
            this.pageIndex = DEFULT_PAGEINDEX;
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {

        return pageSize;
    }

    public void setPageSize(Integer pageSize) {

        if (pageSize == null)
            this.pageSize = DEFULT_PAGESIZE;
        this.pageSize = pageSize;
    }

    public void reset() {
        pageIndex = 1;
        pageSize = 10;
    }
}
