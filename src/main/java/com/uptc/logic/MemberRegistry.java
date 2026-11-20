/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uptc.logic;

import com.uptc.exceptions.DuplicateMemberException;
import com.uptc.exceptions.MemberNotFoundException;
import com.uptc.models.Member;
import com.uptc.models.MembershipType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sergio
 */
public class MemberRegistry implements MemberService{
    private List<Member> members = new ArrayList<>();

    @Override
    public void register(Member member) {
        for (Member member1 : members) {
            if (member1.getId().equals(member.getId())) {
                       throw new DuplicateMemberException(member.getId());
            }
        }
        members.add(member);
    }

    @Override
    public Member findById(String id) {
        for (Member m : members) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        throw new MemberNotFoundException(id);
    }

    @Override
    public void remove(String id) {
        for (Member m : members) {
        if (m.getId().equals(id)) {
            members.remove(m);
            return;
            }
        }
        throw new MemberNotFoundException(id);
    }
    
    @Override
    public List<Member> listAll() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Member> listByType(MembershipType type) {
        List<Member> result = new ArrayList<>();
        for (Member m : members) {
            if (m.getType() == type) {
                result.add(m);
            }
        }
        return result;
    }
    }

