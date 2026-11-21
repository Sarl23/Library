package com.uptc.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MembershipTypeTest {

    @Test
    void studentValues() {
        assertEquals(3, MembershipType.STUDENT.getMaxLoans());
        assertEquals(7, MembershipType.STUDENT.getLoanDays());
        assertEquals(500, MembershipType.STUDENT.getFinePerDay());
    }

    @Test
    void teacherValues() {
        assertEquals(5, MembershipType.TEACHER.getMaxLoans());
        assertEquals(15, MembershipType.TEACHER.getLoanDays());
        assertEquals(300, MembershipType.TEACHER.getFinePerDay());
    }

    @Test
    void externalValues() {
        assertEquals(2, MembershipType.EXTERNAL.getMaxLoans());
        assertEquals(5, MembershipType.EXTERNAL.getLoanDays());
        assertEquals(1000, MembershipType.EXTERNAL.getFinePerDay());
    }
}
