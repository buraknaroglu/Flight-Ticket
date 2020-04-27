package com.flight.ticket.service.base;

import com.flight.ticket.repository.BaseRepository;

/**
 * @author Burak Naroglu
 */

public abstract class BaseServiceProxy<Entity, Specification, Repository extends BaseRepository<Entity>> {

    public abstract Repository getRepository();

    public abstract Specification getSpecification();

}
