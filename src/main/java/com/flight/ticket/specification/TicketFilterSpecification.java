package com.flight.ticket.specification;

import com.flight.ticket.specification.base.BaseFilterSpecification;
import com.flight.ticket.entity.Flight;
import com.flight.ticket.entity.Ticket;
import com.flight.ticket.filter.TicketFilter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.boot.Metadata;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Burak Naroglu
 */

@Component
public class TicketFilterSpecification extends BaseFilterSpecification<Ticket, TicketFilter> {

    @Override
    public Specification<Ticket> filter(TicketFilter filter) {
        return (Specification<Ticket>) (root, criteria, cb) -> {

            List<Predicate> predicates = new ArrayList<>();
            Join<Flight, Metadata> flightJoin = null;

            if (checkNullAndZero(filter.getNumber())) {
                predicates.add(cb.equal(root.get("number"), filter.getNumber()));
            }

            if (StringUtils.isNotBlank(filter.getFlightName())) {
                flightJoin = root.join("flight");
                predicates.add(cb.like(cb.lower(flightJoin.get("name")), "%" + filter.getFlightName().toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
