package com.flight.ticket.specification;

import com.flight.ticket.specification.base.BaseFilterSpecification;
import com.flight.ticket.entity.Route;
import com.flight.ticket.filter.RouteFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Burak Naroglu
 */

@Component
public class RouteFilterSpecification extends BaseFilterSpecification<Route, RouteFilter> {

    @Override
    public Specification<Route> filter(RouteFilter filter) {
        return (Specification<Route>) (root, criteria, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(filter.getName())) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}