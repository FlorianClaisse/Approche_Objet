package org.project.model.gameengine;

import org.project.model.resource.Resources;

public interface CityDelegate {
    boolean canBuy(int price);
    boolean canConstruct(Resources requirements);
    void removeFromStock(Resources resources);
    void addToStock(Resources resources);
    boolean isInShortage();
    void buy(int price);
}
