package cn.ft.ckn.fastmapper.bean;

import lombok.Data;

import java.util.List;

/**
 * @author ckn
 * @date 2022/8/5
 */
@Data
public class PageInfo<T> {
    private List<T> data;
    private int pageNum;
    private int pageSize;
    private int size;
    private int pages;
    private boolean hasPreviousPage = false;
    private boolean hasNextPage = false;
    private int prePage;
    private int nextPage;

    public PageInfo() {
    }

    public PageInfo(List<T> data, Integer pageNum, Integer pageSize, Integer totalCount) {
        this.data = data;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.size = totalCount;
        this.pages = (int) Math.ceil((double) totalCount / pageSize);
        if (pageNum < pages) {
            this.hasNextPage = true;
            this.nextPage = pageNum + 1;
        }
        if (pageNum > 1) {
            this.hasPreviousPage = true;
            this.prePage = pageNum - 1;
        }
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "data=" + data +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", size=" + size +
                ", pages=" + pages +
                ", hasPreviousPage=" + hasPreviousPage +
                ", hasNextPage=" + hasNextPage +
                ", prePage=" + prePage +
                ", nextPage=" + nextPage +
                '}';
    }
}
