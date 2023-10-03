package ir.maktabsharif101.hw8.base.entity;

import java.io.Serializable;

public class BaseEntity<ID extends Serializable> {
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
