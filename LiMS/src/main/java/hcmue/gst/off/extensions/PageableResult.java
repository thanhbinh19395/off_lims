package hcmue.gst.off.extensions;

import org.springframework.data.domain.Page;

/**
 * Created by Thanh Binh on 2/19/2017.
 */
public class PageableResult<T> extends Result<Iterable<T>> {
    private int totalPage;
    private int pageSize;
    private int currentPage;
    public PageableResult(Page<T> data, String message, boolean isSuccess) {
        super(data.getContent(), message, isSuccess);
        pageSize = data.getSize();
        totalPage = data.getTotalPages();
        currentPage = data.getNumber();
    }

    public PageableResult(Page<T> data, boolean isSuccess) {
        super(data.getContent(), isSuccess);
        pageSize = data.getSize();
        totalPage = data.getTotalPages();
        currentPage = data.getNumber();
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
