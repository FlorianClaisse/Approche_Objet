package org.project.model.gameengine;

import org.project.model.resource.Resource;

import java.util.*;

public final class Player implements ManagerDelegate {
    private final List<Resource> resources;

    @Override
    public boolean canConstruct(Set<Resource> requirements) {
        return false;
    }
}
