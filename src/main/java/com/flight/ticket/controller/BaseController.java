package com.flight.ticket.controller;

import com.flight.ticket.service.base.BaseService;
import com.flight.ticket.service.base.ServiceResult;
import com.flight.ticket.specification.base.BaseFilterSpecification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author Burak Naroglu
 */

@Component
public abstract class BaseController<Entity, Filter, Specification extends BaseFilterSpecification<Entity, Filter>, Service extends BaseService<Entity, Filter, Specification>> {

    protected abstract Service getService();


    @PostMapping
    public ResponseEntity save(@RequestBody @Valid Entity entity) throws Exception {
        return createResponse(getService().save(entity));
    }

    @PostMapping("/filter")
    public ResponseEntity filter(Filter filter) {
        return createResponse(getService().filter(filter));
    }

    @GetMapping
    public ResponseEntity findAll() {
        return createResponse(getService().findAll());
    }

    protected ResponseEntity createResponse(ServiceResult serviceResult) {
        return new ResponseEntity<>(serviceResult.getResult(), serviceResult.getHttpStatus());
    }

}

    