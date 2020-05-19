He programado algunos tests y he modificado algunos métodos en función de lo que iba necesitando.

Cosas que quedan por hacer:
1. Terminar los tests de Jugador (en concreto los métodos de acción, el resto está comprobado).
2. Terminar los tests de Colonia (ni siquiera he empezado, pero al ser heredera de Localizacion entiendo que no llevará mucho).
3. Me he dado cuenta de que, cuando se mata a un superviviente, primero hay que eliminarlo de la localización en la que se encuentra (irse()) y luego borrarlo del mazo del jugador que lo posee. Quizá sería interesante crear un método que haga las dos.
4. La localización de Superviviente no se inicia, ¿el superviviente no aparece por defecto en ningún sitio? Entiendo que es la colonia, pero no lo he programado por si me equivoco.
5. Tener en cuenta a cada ronda qué número de superviviente tiene el jugador para controlar el número de dados es un poco cansino. Sugiero crear un método que, cada turno, añada el número de dados necesarios.

Aunque esto no compete a nuestro código, no nos podemos olvidar de los diagramas UML ni de la lista de requisitos. Ya nos organizaremos mañana.

¡Nosotros podemos!
