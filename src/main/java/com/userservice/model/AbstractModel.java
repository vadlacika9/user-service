package com.sportappuserservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@EqualsAndHashCode
@Setter
@ToString(callSuper=true)
public abstract class AbstractModel {
    private String uuid;

    public String getUuid() {
        ensureUuid();
        return uuid;
    }

    public void ensureUuid() {
        if(this.uuid == null) {
            this.uuid = UUID.randomUUID().toString();
        }
    }

    @PrePersist
    public void prePersist() {
        ensureUuid();
    }

}
