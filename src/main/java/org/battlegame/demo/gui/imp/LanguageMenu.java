package org.battlegame.demo.gui.imp;

import javafx.util.Duration;
import org.battlegame.demo.gui.build.OptionDTO;
import org.battlegame.demo.gui.build.OptionListener;
import org.battlegame.demo.gui.build.OptionMenuContainer;
import org.battlegame.demo.gui.transitions.ETransitions;
import org.battlegame.demo.gui.transitions.IOptionTransitional;
import org.battlegame.demo.observerMenu.EMenu;
import org.battlegame.demo.observerMenu.MenuController;

import java.util.List;

public class LanguageMenu extends OptionMenuContainer implements IOptionTransitional {
    public LanguageMenu(MenuController menuController) {
        super(menuController,
                List.of(
                        new OptionListener(
                                new OptionDTO("English")
                        ),
                        new OptionListener(
                                new OptionDTO("Spanish")
                        ),
                        new OptionListener(
                                new OptionDTO("Back")
                        )
                )
        );
    }

    @Override
    public boolean onEnterPressed() {
        switch (getOptionIndex()) {
            case 0,1 -> toNone();
            case 2 -> toOptionsMenu();
        }
        return true;
    }


    public void toOptionsMenu() {
        getMenuController().changeCurrentMenuBehavior(EMenu.OPTIONS);
    }

    public void toNone() {
        //getMenuController().changeCurrentMenuBehavior(EMenu.NONE);
        System.out.println("Language OptionListener Selected!");
    }

    @Override
    public void startedTransition() {
        getOptionListenerList().forEach(optionListener ->{
            ETransitions.FADE_IN.startTransition(Duration.millis(100), optionListener);
        });
    }

    @Override
    public void exitedTransition() {
        getOptionListenerList().forEach(optionListener ->{
            ETransitions.FADE_OUT.startTransition(Duration.millis(200), optionListener);
        });
    }
}
