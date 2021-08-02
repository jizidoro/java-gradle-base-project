package com.comrades.persistence.dataaccess;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ConfigurationProperties(prefix = "r2dbc")
public class R2DBCConfigurationProperties {

    @NotEmpty
    private String url;

    private String user;
    private String password;

}
