# Simulazione di uno Sciame di Robot

Questo progetto è una libreria Java per la simulazione di uno sciame di robot che si muovono su una superficie piana. I robot possono percepire l'ambiente, segnalare condizioni, muoversi secondo diverse strategie e seguire istruzioni definite da un linguaggio specifico. Il linguaggio permette di impartire comandi come movimento verso coordinate specifiche, movimento casuale, segnalazione di condizioni, iterazioni e condizioni di arresto basate sulla percezione dell'ambiente.

# Implementazione

Il progetto è sviluppato seguendo i principi SOLID e Clean Code per garantire modularità, manutenibilità ed estendibilità.
La classe Robot gestisce il movimento e le segnalazioni dei robot.
Le istruzioni sono definite tramite l'interfaccia Commands, con metodi per l'esecuzione dei comandi.
ProgramExecutor tiene traccia del flusso delle istruzioni e gestisce iterazioni e condizioni.
Coordinates e Direction definiscono rispettivamente la posizione e la direzione dei robot.
Circle e Rectangle implementano Area per segnalare condizioni ambientali rilevanti.
FileReader e ParsingCommands gestiscono il caricamento e il parsing delle istruzioni e delle aree.
RobotSwarm coordina lo sciame di robot e utilizza ExecutorService per il supporto al multithreading.
L'interfaccia JavaFX permette di avviare la simulazione, visualizzare i robot e caricare file di istruzioni tramite un'interfaccia grafica semplice e interattiva.
Il progetto può essere eseguito con i comandi gradlew build e gradlew run.
