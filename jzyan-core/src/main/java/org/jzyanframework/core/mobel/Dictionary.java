package org.jzyanframework.core.mobel;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 字典基础类
 *
 * @author : jzyan
 */
public class Dictionary implements Serializable {

    @ApiModelProperty("字典值")
    private String value;
    @ApiModelProperty("字典名称")
    private String label;
    @ApiModelProperty("是否选中")
    private Boolean checked;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "value='" + value + '\'' +
                ", label='" + label + '\'' +
                ", checked=" + checked +
                '}';
    }
}
