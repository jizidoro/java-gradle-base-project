package com.comrades.core.bases;

import java.io.Serializable;

@FunctionalInterface
public interface ILoggedUseCase {
    Serializable loggedEntityId();
}
