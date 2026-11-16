/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uptc.logic;

import com.uptc.models.Member;
import com.uptc.models.MembershipType;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface MemberService {
    
    void register(Member member);
    
    Member findById(String id);
    
    void remove(String id);
        
    List<Member> listAll();
    
    List<Member> listByType(MembershipType type);

}
