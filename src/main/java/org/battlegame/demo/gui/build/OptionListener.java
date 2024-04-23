package org.battlegame.demo.gui.arq;

public class OptionListener {
    private final Option option;
    private final String mark = "";

    public OptionListener(Option option) {
        this.option = option;
    }

    /**
     * TODO: Intento de forzar un acoplamiento usando el patrón Observer, la forma correcta sería usando el patrón Strategy o el patrón State, o llendo a algo mas complejo, una mezcla del patron Visitor y el patron Strategy
     * La idea de este metodo abstracto es que por medio de el se comunicará el estado de todas las opciones (y todas pueden hacer algo, por ejemplo revisar si están seleccionadas y, dependiendo de desde donde fue llamada, el comportamiento sea distinto)
     */
    //public abstract void onOptionNotify();

    /**
     * Implementacion basica para la notificacion a las opciones, en caso de ser la seleccionada se cambia el texto de la opcion,,
     * La nocion basica de añadir algo al texto no me gustó asi que lo invertí, se le añade un texto (un espacio) a toda opcion  no seleccionada, de forma que la opcion seleccionada no tenga espacio y quede mas bonita
     */
    public void onOptionEntered(){
        option.changeSelection();
        String auxOptionText = option.getOption().replace(mark, "");
        option.setOption(option.isSelected()? auxOptionText : mark + auxOptionText);
    }

    public Option getOption() {
        return option;
    }
}
