package org.battlegame.demo.gui.utils;

import org.battlegame.demo.gui.build.OptionMenuContainer;
import org.battlegame.demo.observerMenu.EMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuRepo {
    private static MenuRepo instance = new MenuRepo();
    private Map<EMenu, OptionMenuContainer> optionMenuContainers = new HashMap<>();

    public MenuRepo() {
    }

    public static MenuRepo getInstance() {
        return instance;
    }

    public void addOptionMenuContainer(EMenu menu, OptionMenuContainer optionMenuContainer) {
        optionMenuContainers.put(menu, optionMenuContainer);
    }

    public void addOptionsMenuContainer(List<Map.Entry<EMenu, OptionMenuContainer>> optionMenuContainers) {
        optionMenuContainers.forEach(entry -> this.optionMenuContainers.put(entry.getKey(), entry.getValue()));
    }

    public OptionMenuContainer getOptionMenuContainer(EMenu menu) {
        return optionMenuContainers.get(menu);
    }

    public List<OptionMenuContainer> getOptionMenuContainers() {
        return new ArrayList<>(optionMenuContainers.values());
    }

    public OptionMenuContainer findMenu(EMenu menu) {
        return optionMenuContainers.entrySet().stream()
                .filter(entry -> entry.getKey().equals(menu))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }

    public void removeOptionMenuContainer(EMenu menu) {
        optionMenuContainers.remove(menu);
    }

    public void clear() {
        optionMenuContainers.clear();
    }

    public boolean isEmpty() {
        return optionMenuContainers.isEmpty();
    }

    public boolean containsMenu(EMenu menu) {
        return optionMenuContainers.containsKey(menu);
    }

    public boolean containsOptionMenuContainer(OptionMenuContainer optionMenuContainer) {
        return optionMenuContainers.containsValue(optionMenuContainer);
    }

    public int size() {
        return optionMenuContainers.size();
    }


}
