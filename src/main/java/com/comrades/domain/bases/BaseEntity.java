package com.comrades.domain.bases;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BaseEntity {
    public @Id UUID id;
    public UUID key;
    public String value;

}
