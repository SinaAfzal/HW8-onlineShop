package ir.maktabsharif101.hw8.base.service;

import ir.maktabsharif101.hw8.base.entity.BaseEntity;

import java.io.Serializable;
import java.sql.SQLException;

public interface BaseService<ID extends Serializable,Type extends BaseEntity<ID>> {
    Type save(Type entity) throws SQLException;
    void delete(ID id) throws SQLException;
    int update(Type entity) throws SQLException;
    boolean doesExist(ID id) throws SQLException;
}
