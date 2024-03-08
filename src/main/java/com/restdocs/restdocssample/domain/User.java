package com.restdocs.restdocssample.domain;

import lombok.Builder;

@Builder
public record User (int id, String name) {
}
