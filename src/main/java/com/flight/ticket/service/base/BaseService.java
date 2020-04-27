package com.flight.ticket.service.base;

import com.flight.ticket.specification.base.BaseFilterSpecification;
import com.flight.ticket.repository.BaseRepository;

import java.util.List;

/**
 * @author Burak Naroglu
 */

public abstract class BaseService<Entity, Filter, Specification extends BaseFilterSpecification<Entity, Filter>> extends BaseServiceProxy<Entity, Specification, BaseRepository<Entity>> {

    public ServiceResult<List<Entity>> filter(Filter filter) {
        List<Entity> entities = getRepository().findAll(getSpecification().filter(filter));
        return new ServiceResult<>(entities);
    }

    public ServiceResult<Entity> save(Entity entity) {
        Entity save = getRepository().save(entity);
        return new ServiceResult<>(save);
    }

    public ServiceResult<List<Entity>> findAll() {
        List<Entity> entities = getRepository().findAll();
        return new ServiceResult<>(entities);
    }

}

