package br.com.management.userlogin.shared;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    @CreatedDate
    @Column(name = "created")
    protected LocalDateTime created;

    @LastModifiedDate
    @Column(name = "modified")
    protected LocalDateTime modified;

    @Column(name = "last_login")
    protected LocalDateTime lastLogin;
}
