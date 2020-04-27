package com.flight.ticket.specification.base;

import org.springframework.data.jpa.domain.Specification;

/**
 * @author Burak Naroglu
 */

public abstract class BaseFilterSpecification<Entity, Filter> extends BaseSpecification {

    public abstract Specification<Entity> filter(Filter filter);

}