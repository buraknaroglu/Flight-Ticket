package com.flight.ticket.specification;

import com.flight.ticket.specification.base.BaseFilterSpecification;
import com.flight.ticket.entity.Airline;
import com.flight.ticket.entity.Flight;
import com.flight.ticket.filter.FlightFilter;
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
public class FlightFilterSpecification extends BaseFilterSpecification<Flight, FlightFilter> {

    @Override
    public Specification<Flight> filter(FlightFilter filter) {
        return (Specification<Flight>) (root, criteria, cb) -> {

            List<Predicate> predicates = new ArrayList<>();
            Join<Airline, Metadata> airlineJoin = null;

            if (StringUtils.isNotBlank(filter.getName())) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
            }

            if (StringUtils.isNotBlank(filter.getAirlineName())) {
                airlineJoin = root.join("airline");
                predicates.add(cb.like(cb.lower(airlineJoin.get("name")), "%" + filter.getAirlineName().toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
