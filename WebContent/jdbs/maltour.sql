-- username: Maltour
-- password: adminadmin
DROP database `agenzia`;
CREATE DATABASE IF NOT EXISTS `agenzia`;
USE `agenzia`;

CREATE TABLE `utente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `passwordhash` char(40) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `admin` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`username`),
  UNIQUE KEY (`email`)
);

CREATE TABLE `servizio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descrizione` mediumtext NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `offerta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descrizione` longtext NOT NULL,
  `prezzoCent` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY (`nome`),
  FULLTEXT KEY (`nome`,`descrizione`)
);

CREATE TABLE `offerta_servizio` (
  `idofferta` int(11) NOT NULL,
  `idservizio` int(11) NOT NULL,
  PRIMARY KEY (`idofferta`,`idservizio`),
  CONSTRAINT FOREIGN KEY (`idofferta`) REFERENCES `offerta` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FOREIGN KEY (`idservizio`) REFERENCES `servizio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

LOCK TABLES `utente` WRITE;
INSERT INTO `utente` VALUES
 (1,'Angela',SHA1('titolare01'),'TITOLARE','angela@maltour.com',1),
 (2,'TERESA',SHA1('dipendente02'),'DIPENDENTE01','teresa@maltour.com',1),
 (3,'LUCIA',SHA1('dipendente03'),'DIPENDENTE02','lucia@maltour.com',0);
UNLOCK TABLES;

LOCK TABLES `servizio` WRITE;
INSERT INTO `servizio` VALUES 
(1,'TOUR','tour enogastronomici e culturali, alla scoperta dell italia'),
(2,'CITTA EUROPEE','Città grandi e piccole, ricche di storia e cultura, sede di grandi eventi e nuove aperture, paesaggi pittoreschi e luoghi incantati a due passi dall’Italia: date un’occhiata alle 19 Città Europee da vedere nel 2019 e regalatevi un viaggio bellissimo, che possa restarvi nella mente, nel cuore e nella memoria…'),
(3,'DESTINAZIONE MARE','Ci sono tantissimi luoghi che vi attendono, qui abbiamo realizzato una piccola guida con qualche destinazione che può tornarvi utile la prossima estate.'),
(4,'MINICROCIERE','Hai solo qualche giorno libero e stai pensando ad una vacanza in alto mare? Di seguito ti proponiamo un elenco di mini crociere. Si tratta di Crociere che durano meno di 7 giorni, ma avrai la sensazione di viverne molti di più.');
UNLOCK TABLES;

LOCK TABLES `offerta` WRITE;
INSERT INTO `offerta` VALUES 
(1,'Singapore & Bali','9 notti / 11 giorni - Availability : Su Richiesta - volo da Roma',235000),
(2,'Cuba Havana & Cayo Santa Maria','9 notti / 12 giorni - Availability : Su Richiesta - volo da Roma',99900),
(3,'Miami & Bahamas','8 notti / 9 giorni - Availability : Su Richiesta - volo da Roma',235000),
(4,'Mauritius','7 notti / 9 giorni - Availability : Su Richiesta - volo da Roma',190000),
(5,'Napoli, Ischia e Caserta','Questo tour vi permetterà di conoscere un po’ in più la Campania.. Napoli, Caserta e l’isola di Ischia sono solo alcune delle meraviglie di questa regione. Scoprite con noi quanto di più bello questa regione può offrirvi!',00),
(6,'Nord Italia Tour','Il tour si fa strada attraverso incantevoli località come Milano e Torino; costeggia laghi meravigliosi come quelli di Como. Smetti di pensarci troppo: fai rotta verso uno dei percorsi più suggestivi del vecchio continente.',00),
(7,' Venezia , Lago di Garda e Milano','Un tour speciale alla ricerca della tranquillità , della cultura e della bellezza paesaggistica dell’Italia',00),
(8,'Milano , Venezia e Firenze','Un tour fatto apposta per chi desidera essere cullato durante il viaggio : cene in ristoranti di classe , hotel lussuosi e viaggi in 1a classe',00),
(9,'Tour del cioccolato a Perugia','Oltre a famosi prodotti tipici , l’ Italia offre anche un buon cioccolato , e Perugia è il fulcro di questo buonissimo prodotto',00),
(10,'Sorrento e Amalfi con lezione di cucina','Tour alla scoperta dei sapori e cultura di Sorrento ed Amalfi con una piacevole lezione di cucina e visite guidate delle città simbolo della Campania',00),
(11,'Tour del vino in Toscana','Il vino italiano è forse uno dei più pregiati vini al mondo , se non il più buono . Questo tour vi offre la possibilità di assaggiare i prodotti tipici della Toscana tra i quali proprio il Chianti ',00),
(12,'Cracovia','4 notti / 5 giorni - PARTENZA 18/09/2019 - RITORNO 21/09/2019 volo da Napoli - hotel:Corner Hotel ',23900),
(13,'Praga','4 notti / 5 giorni - PARTENZA 02/11/2019 - RITORNO 06/11/2019 volo da Napoli - hotel: Kings Residence  ',19900),
(14,'Campania, Sorrento e Capri','Alle ore 8.30 partenza da Castellammare di Stabia / Sorrento. Arrivo a Capri, fermata a Grotta Bianca per fare un bagno, poi attraversare i famosi Faraglioni e proseguire fino a Nerano, riserva marina di Punta Campanella, con la possibilità di fare uno spuntino o un pranzo nella cambusa. Proseguire per Positano passando per le isole di Li Galli. Sbarco opzionale a Positano da spears per 2 ore e 30 minuti o andare su Amalfi. Godetevi il vostro tempo libero per circa 1 ora e 30 minuti. Il ritorno è previsto nel tardo pomeriggio attraverso Positano, lungo la costa per vedere le magnifiche spiagge e calette, villaggi e isole di pescatori',24900),
(15,'Puglia: Gargano ed Isole Tremiti','Partenza da Manfredonia in direzione Vieste per ammirare le baie più belle della zona: Mattinatella, Baia di Zagare, Pugnochiuso. Cena tipica in barca ormeggiata nel porto di Vieste. Il secondo giorno, in direzione delle Isole Tremiti, vivrete un’esperienza unica in stretto contatto con il mare, circumnavigando le tre isole principali che formano l’arcipelago: San Domino, San Nicola e Capraia. Al terzo giorno ritorno a Manfredonia, arrivo alle ore 20.30.',70300),
(16,'Lazio: dal Tevere ad Ostia Antica','Un viaggio affascinante alla scoperta del Tevere in modo nuovo e originale, apprezzando la flora e la fauna sorprendentemente ancora incontaminati. Procediamo visitando gli scavi di Ostia Antica, con la sua immensa zona archeologica perfettamente conservata. Alle 9.30 partenza da Roma, arrivo a Ostia alle 11.30; Visita guidata degli scavi archeologici di Ostia Antica e pranzo al sacco. Alle 16.00 torna a Roma in treno.',64200);
UNLOCK TABLES;

LOCK TABLES `offerta_servizio` WRITE;
INSERT INTO `offerta_servizio` VALUES (5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,2),(12,2),(13,2),(1,3),(2,3),(3,3),(4,3),(14,4),(15,4),(16,4);
UNLOCK TABLES;
