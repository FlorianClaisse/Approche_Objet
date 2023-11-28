package org.project.model.gameengine;

import org.project.model.resource.Resource;

import java.util.List;
public interface ManagerDelegate {
    boolean canConstruct(List<Resource> requirements);
    void removeFromStock(List<Resource> resources);
    void addToStock(List<Resource> resources);
}
