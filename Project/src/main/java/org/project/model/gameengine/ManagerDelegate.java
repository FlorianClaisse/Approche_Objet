package org.project.model.gameengine;

import org.project.model.resource.Resource;

import java.util.Set;

public interface ManagerDelegate {
    boolean canConstruct(Set<Resource> requirements);
    void removeFromStock(Set<Resource> resources);
}
