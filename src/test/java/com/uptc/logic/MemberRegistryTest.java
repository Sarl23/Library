/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.uptc.logic;

import com.uptc.exceptions.DuplicateMemberException;
import com.uptc.exceptions.MemberNotFoundException;
import com.uptc.models.Member;
import com.uptc.models.MembershipType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author sergio
 */
public class MemberRegistryTest {
    private MemberService service;
    
    public MemberRegistryTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        service= new MemberRegistry();
    }
    
    @AfterEach
    public void tearDown() {
    }
    @Test
    public void registerAndFindById() {
        Member ana = new Member("1", "Ana", MembershipType.STUDENT);
        service.register(ana);
        assertEquals(ana, service.findById("1"));
    }
    @Test
    public void registerDuplicateIdThrows() {
        service.register(new Member("1", "Ana", MembershipType.STUDENT));
        assertThrows(DuplicateMemberException.class,
                () -> service.register(new Member("1", "Luis", MembershipType.TEACHER)));
    }
    
    @Test
    public void removeDeletesMember() {
        service.register(new Member("1", "Ana", MembershipType.STUDENT));
        service.register(new Member("2", "Andres", MembershipType.STUDENT));
        service.remove("1");
        assertEquals(0, service.listAll().size());
    }
    @Test
    public void removeNotFoundThrows() {
        assertThrows(MemberNotFoundException.class, () -> service.remove("9"));
    }
    @Test
    public void listByTypeReturnsOnlyThatType() {
        service.register(new Member("1", "Ana", MembershipType.STUDENT));
        service.register(new Member("2", "Luis", MembershipType.TEACHER));
        service.register(new Member("3", "Eva", MembershipType.STUDENT));
        assertEquals(2, service.listByType(MembershipType.STUDENT).size());
    }


    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
