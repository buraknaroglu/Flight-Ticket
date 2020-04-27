package com.flight.ticket.specification.base;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Burak Naroglu
 */

public class BaseSpecification {

    public Boolean checkNullAndZero(Integer value) {
        return checkZeroValue(value != null ? value.toString() : null);
    }

    private Boolean checkZeroValue(String value) {
        if (StringUtils.isNotBlank(value) && !value.equals("0")) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
