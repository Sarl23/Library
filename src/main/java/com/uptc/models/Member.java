package com.uptc.models;

import com.uptc.exceptions.InvalidMemberException;
//
public class Member {

    private final String id;
    private final String name;
    private final MembershipType type;

    public Member(String id, String name, MembershipType type) {
        if (id == null || id.isBlank()) {
            throw new InvalidMemberException("El id del socio no puede estar vacío");
        }
        if (name == null || name.isBlank()) {
            throw new InvalidMemberException("El nombre del socio no puede estar vacío");
        }
        if (type == null) {
            throw new InvalidMemberException("El tipo de socio no puede ser nulo");
        }
        this.id = id.trim();
        this.name = name.trim();
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public MembershipType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Member{id='" + id + "', name='" + name + "', type=" + type + "}";
    }
}
