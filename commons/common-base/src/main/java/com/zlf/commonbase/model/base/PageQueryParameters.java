package com.zlf.commonbase.model.base;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;

/**
 * 分页查询的参数包含页码和分页大小
 *
 * @author zhenglifei
 */
public abstract class PageQueryParameters implements Parameters {

    @ApiModelProperty(value = "分页页码")
    @Min(1)
    private int current = 1;

    @ApiModelProperty(value = "分页大小")
    @Min(1)
    private int pageSize = 10;

    protected PageQueryParameters() {
    }

    protected PageQueryParameters(int current, int pageSize) {
        this.current = current;
        this.pageSize = pageSize;
    }

    public int getCurrent() {
        return current;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
