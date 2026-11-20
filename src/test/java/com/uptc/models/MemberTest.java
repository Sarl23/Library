package com.uptc.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.uptc.exceptions.InvalidMemberException;
import org.junit.jupiter.api.Test;

class MemberTest {

    @Test
    void createsValidMember() {
        Member m = new Member("1", "Ana", MembershipType.STUDENT);
        assertEquals("1", m.getId());
        assertEquals("Ana", m.getName());
        assertEquals(MembershipType.STUDENT, m.getType());
    }

    @Test
    void rejectsEmptyId() {
        assertThrows(InvalidMemberException.class, () -> new Member(" ", "Ana", MembershipType.STUDENT));
    }

    @Test
    void rejectsNullName() {
        assertThrows(InvalidMemberException.class, () -> new Member("1", null, MembershipType.STUDENT));
    }

    @Test
    void rejectsNullType() {
        assertThrows(InvalidMemberException.class, () -> new Member("1", "Ana", null));
    }
}
