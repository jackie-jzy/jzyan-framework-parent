package org.jzyanframework.core.mobel;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 分页基础类
 *
 * @author : jzyan
 */
public class Page implements Serializable {

    @ApiModelProperty("每页显示条数，默认10，最大100")
    private long limit = 10;
    @ApiModelProperty("当前页")
    private long current = 1;

    /**
     * get limit
     *
     * @return
     */
    public long getLimit() {
        return limit;
    }

    /**
     * set limit
     *
     * @param limit
     */
    public void setLimit(long limit) {
        if (limit > 100) {
            this.limit = 100;
        } else if (limit < 1) {
            this.limit = 10;
        } else {
            this.limit = limit;
        }
    }

    /**
     * get current
     *
     * @return
     */
    public long getCurrent() {
        return current;
    }

    /**
     * set current
     *
     * @param current
     */
    public void setCurrent(long current) {
        if (current < 1) {
            this.current = 1;
        } else {
            this.current = current;
        }
    }

    @Override
    public String toString() {
        return "Page{" +
                "limit=" + limit +
                ", current=" + current +
                '}';
    }
}
