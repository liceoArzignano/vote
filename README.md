Vote
====

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/83fda9959b364515b3f0bce01f3f5277)](https://www.codacy.com/app/LiceoArzignano/vote?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=liceoArzignano/vote&amp;utm_campaign=Badge_Grade)

### Requisiti
* java 8
* gradle

### Compilazione
```./build.sh```
La cartella _dist/vote_ conterrà tutti i file necessari per l'esecuzione

### Esecuzione
Linux e Unix
```./dist/vote/bin/vote```
Windows
```dist/vote/bin/vote.bat```

### Manutenzione elenco docenti/classi
Il file _DATA/Rooms.txt_ contiene un elenco delle varie classi, ciascuna scritta su una riga e nel formato _**N**(umero)**C**(arattere maiuscolo)**N**(umero).
Dentro la cartella _DATA/teachers_ devono essere collocati i file relativi ai docenti. Il file deve avere il nome nel formato CognomeNome.txt. È importante che ogni parola inizi con la lettera maiuscola. Dentro ciascuno di questi file vanno inserite le classi alle quali il docente è assegnato. Come per il file _Rooms.txt_, il formato deve essere _**N**(umero)**C**(arattere maiuscolo)**N**(umero)_.

### Utilizzo
Una volta aperto il programma selezionare la classe votante ed inserire il numero di votanti nell'apposita casella di testo. Verrà dunque caricata un'interfaccia che consentirà a ciascun votante di selezionare 2* docenti tra quelli che risultano assegnati alla classe selezionata. Quando tutti hanno votato il programma avviserà il completamento della raccolta di voti e i risultati saranno disponibili nel file indicato dal programma stesso (che si trova nella cartella _dist/vote/bin/RESULTS_). Il file _.csv_ contenente i risultati della votazione è importabile in Excel o Calc dai quali si potranno effetuare tutte le operazioni di analisi dei risultati necessarie.  



