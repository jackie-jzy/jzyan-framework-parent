package org.jzyanframework.core.enums;

/**
 * <p>SHA加密类型</p>
 *
 * @author licoy.cn
 * @version 2018/9/6
 */
public enum SHAEncryptTypeEnum {

    SHA224("sha-224"),
    SHA256("sha-256"),
    SHA384("sha-384"),
    SHA512("sha-512"),
    ;

    public String value;

    SHAEncryptTypeEnum(String value) {
        this.value = value;
    }
}
