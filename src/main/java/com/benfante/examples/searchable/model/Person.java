package com.benfante.examples.searchable.model;

import java.util.UUID;

public record Person(Long id, String firstName, String lastName, UUID uuid) {
}