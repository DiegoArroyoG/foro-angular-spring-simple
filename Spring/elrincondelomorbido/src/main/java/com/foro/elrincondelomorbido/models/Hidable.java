package com.foro.elrincondelomorbido.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("hidden")
public interface Hidable {
    boolean isHidden();
}
