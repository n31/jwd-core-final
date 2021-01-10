package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.EntityFactory;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {
    private Long lastId = 1L;

    private CrewMemberFactory() {}
    private static CrewMemberFactory instance;
    public static CrewMemberFactory getInstance() {
        if (instance == null) {
            instance = new CrewMemberFactory();
        }
        return instance;
    }

    @Override
    public CrewMember create(Object... args) {
        Long role = (Long)args[0];
        String name = (String)args[1];
        Long rank = (Long)args[2];
        return new CrewMember(Role.resolveRoleById(role), name, Rank.resolveRankById(rank), lastId++);
    }
}
