package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CrewCrudTest {

    @Mock
    private CrewMember crewMember;

    @Mock
    private CrewCrud crewCrud = CrewCrud.getInstance();

    @Test
    public void testCreateCrewMember() {
        crewCrud.createCrewMember(crewMember);
        verify(crewCrud).createCrewMember(crewMember);
    }

    @Test
    public void testFindAllCrewMembers() {
        List<CrewMember> result = crewCrud.findAllCrewMembers();
        verify(crewCrud).findAllCrewMembers();
        Assert.assertEquals(new ArrayList<>(), result);
    }
}
