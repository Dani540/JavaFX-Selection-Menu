
** La idea es hacer representaciones de texto en pantalla con la interactividad de un juego de aventura conversacional **

# Estructura de los archivos

* La idea es que cada archivo sea componetizado, es decir, que cada archivo pueda ponerse dentro de otro de manera indiscriminada,
de forma que luego cada objeto dentro del json es un componente que se puede renderizar en pantalla.

## Estructura de un menu

* Un menú es un objeto que tiene un id, nombre y un array de opciones, cada opción es un objeto que tiene un id, nombre, texto, y direcciones from_id y to_id, de modo que:

```json
{
    "Menus":[
    {
        "id": "0",
        "name": "MAIN_MENU",
        "options": [
            {
                "id": 0,
                "name": "opcion0",
                "text": "Options",
                "from_menu_id": 0,
                "to_menu_id": 1 < -- Este es el id del menu de opciones
            },
            {
                "id": 2,
                "name": "opcion1",
                "text": "Salir",
                "from_id": 0,
                "to_id": -1 < -- El negativo indica la salida de la ejecucion del menu
            }
        ]
    },
    {
        "id": "1",
        "name": "OPTIONS_MENU",
        "options": [
            {
                "id": 0,
                "name": "opcion0",
                "text": "Volver",
                "from_menu_id": 1,
                "to_menu_id": 0
            }
        ]
    }
    ]
}

* Un dialogo es una agrupacion de textos, de modo que:

{
    "Dialogs":[
        {
            "id": "0",
            "character_id": "0",
            "dialogs": [
                {
                    "id": 0,
                    "text": "Hola, soy un texto de dialogo"
                },
                {
                    "id": 1,
                    "text": "Hola, soy otro texto de dialogo"
                }
            ]
        },
        {
            "id": "1",
            "character_id": "1",
            "dialogs": [
                {
                    "id": 0,
                    "text": "Hola, soy un texto de dialogo"
                },
                {
                    "id": 1,
                    "text": "Hola, soy otro texto de dialogo"
                }
            ]
        }
    ]
}
