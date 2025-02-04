English: 

# Simulation of a Robot Swarm
This project is a Java library for simulating a swarm of robots moving on a flat surface. The robots can perceive the environment, signal conditions, move according to different strategies, and follow instructions defined by a specific language. The language allows issuing commands such as movement toward specific coordinates, random movement, condition signaling, iterations, and stop conditions based on environmental perception.

# Implementation
The project is developed following SOLID principles and Clean Code practices to ensure modularity, maintainability, and extensibility. 
The Robot class manages robot movement and signaling. 
The instructions are defined through the Commands interface, with methods for executing commands. 
ProgramExecutor tracks the flow of instructions and handles iterations and conditions. 
Coordinates and Direction define the position and direction of the robots. 
Circle and Rectangle implement Area to signal relevant environmental conditions. 
FileReader and ParsingCommands handle loading and parsing of instructions and areas. 
RobotSwarm coordinates the robot swarm and uses ExecutorService to support multithreading. 
The JavaFX interface allows users to start the simulation, visualize the robots, and load instruction files through a simple and interactive graphical interface. 
The project can be executed using the commands gradlew build and gradlew run.



Italian:
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


