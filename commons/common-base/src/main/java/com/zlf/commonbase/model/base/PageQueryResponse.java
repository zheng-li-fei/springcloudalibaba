package com.zlf.commonbase.model.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * 分页查询结果
 *
 * @author zhenglifei
 */
@ApiModel(description = "分页查询结果")
public class PageQueryResponse<T> implements Response {

    @ApiModelProperty(value = "分页页码")
    private int current;

    @ApiModelProperty(value = "分页大小")
    private int pageSize;

    @ApiModelProperty(value = "查询结果数据")
    private Collection<T> content;

    @ApiModelProperty(value = "总数据条数")
    private long total;

    protected PageQueryResponse() {

    }

    protected PageQueryResponse(PageQueryParameters parameters, Collection<T> content, long total) {
        this.current = parameters.getCurrent();
        this.pageSize = parameters.getPageSize();
        this.content = content;
        this.total = total;
    }

    public static <T> PageQueryResponse<T> create(PageQueryParameters parameters) {
        return new PageQueryResponse<>(parameters, Collections.emptyList(), 0);
    }

    public static <T> PageQueryResponse<T> create(PageQueryParameters parameters, Collection<T> content, long totalCount) {
        return new PageQueryResponse<>(parameters, content, totalCount);
    }

    public int getCurrent() {
        return current;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Collection<T> getContent() {
        return content;
    }

    public long getTotal() {
        return total;
    }

    protected void setCurrent(int current) {
        this.current = current;
    }

    protected void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    protected void setContent(Collection<T> content) {
        this.content = content;
    }

    protected void setTotal(int total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PageQueryResponse<?> that = (PageQueryResponse<?>) o;
        if (current != that.current) {
            return false;
        }
        if (pageSize != that.pageSize) {
            return false;
        }
        if (total != that.total) {
            return false;
        }
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(current, pageSize, content, total);
    }
}
