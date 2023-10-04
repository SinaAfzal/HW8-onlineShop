package ir.maktabsharif101.hw8.base.service;

import ir.maktabsharif101.hw8.base.entity.BaseEntity;
import ir.maktabsharif101.hw8.base.repository.BaseRepository;


import java.io.Serializable;
import java.sql.SQLException;

public class BaseServiceImpl<ID extends Serializable, R extends BaseRepository<ID, Type>, Type extends BaseEntity<ID>> implements BaseService<ID, Type> {

    protected final R REPOSITORY;

    public BaseServiceImpl(R repository) {
        REPOSITORY = repository;
    }


    @Override
    public Type save(Type entity) throws SQLException {
        return REPOSITORY.save(entity);
    }

    @Override
    public void delete(ID id) throws SQLException {
        REPOSITORY.delete(id);
    }

    @Override
    public int update(Type entity) throws SQLException {
        return REPOSITORY.update(entity);
    }

    @Override
    public boolean doesExist(ID id) throws SQLException {
        return REPOSITORY.doesExist(id);
    }

}
