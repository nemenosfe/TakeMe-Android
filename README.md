# TakeMe-Android

## Pantalles
  - Login (Facebook | Google | Twitter)
  - Gustos (Categorias | Ciudades)
  - Drawer
  - EventFeed (Todos | Asistire | Recomendados)
  - Evento (Información detallada)
  - Profile (Logros | Recompensas)
  - Market (Recompensas por nivel)
  - Check-in (Historial | Check-in)
  
## Accions per Pantalla
### Login
  > Pantalla senzilla amb el logo de l'aplicació amb 3 botons per poder fer login a través de Facebook, Google o Twitter, cadascuna de les 3 xarxes socials són usuaris independents. Hi ha persistència de sessió entre execucions, només es podrà accedir de nou al Login si es fa logout o es reinstal·la l'aplicació.
>
> Es distingeixen per l'user-id i el provider, que pot ser qualsevol de les xarxes socials.

## Gustos
  > Toolbar: Si es tracta d'un usuari nou, es mostrarà aquesta activitat sense l'opció de tirar cap enrere, però podrà ometre aquest formulari, on només es demanen els gustos de la persona en relació a les categories i ubicació dels esdeveniments. En canvi, si fos un usuari recurrent, no se li mostrarà aquesta pantalla excepte si accedeix des del drawer de l'aplicació, en aquest cas no tindrà l'opció d'ometre només podrà tornar enrere o desar els canvis.
  >
  > Gustos i ubicació: Els gustos de l'usuari es mostraran en una ListView, que s'omplirà en funció del que trïi l'usuari al dialog que sobre al fer clic sobre el botó que posa "Seleccionar categorías", de la mateixa forma amb les ciutats d'interes. Les categories són totes les que eventful ens permet utilitzar, però per les ciutats son les 9 ciutats més poblades d'Espanya.
  >
  > Gustos i ubicació: Els gustos de l'usuari es mostraran en una ListView, que s'omplirà en funció del que triï l'usuari al dialog que sobre al fer clic sobre el botó que posa "Seleccionar categorías", de la mateixa forma amb les ciutats d'interès. Les categories són totes les que eventful ens permet utilitzar, però per les ciutats són les 9 ciutats més poblades d'Espanya.
  >
  > No es guarden els gustos fins que no es selecciona el boto "Guardar", el botó "OK" del dialog només permetre seleccionar els gustos que enviariem a la API.
  
## Drawer
  > Drawer de 6 opcions (Buscar eventos, Mi perfil, Cambiar Takes, Mis eventos, Preferencias y Desconectar).
  >
  > "Buscar eventos" ens permet accedir als 3 feeds d'esdeveniments que compte la nostra aplicació (Todos|Asistiré|Recomendados)
  >
  > "Mi perfil" permet accedir al perfil de l'usuari, a la mateixa pantalla trobarà els seus logros i les recompenses que ha obtingut.
  >
  > "Cambiar takes": Servirà per intercanviar els "takes" la moneda de bescanvi utilitzada en aquesta app que s'aconsegueix assistint a esdeveniments i aconseguint logros.
  >
  > Amb "Mis eventos" es podrà veure un historial dels esdeveniments de l'usuari, aquest historial en situacions normals i sense la utilització del "GOD mode, que més tard s'explicarà", només mostrarà esdeveniments passats que s'ha indicat que s'assistiria i esdeveniments presents que ja s'ha fet un check-in. L'altre pestanya que té aquesta pantalla és on es fan els check-in, aquí només sortiran esdeveniments presents que no s'ha fet check-in i també esdeveniments futurs. En el moment que es fa un check-in es refresquen les dues pestanyes.
  > Per poder fer un check-in és necessari:
  - Trobar-se a 500 metres del punt de check-in dels esdeveniments.
  - Estar dintre del rang d'hores que dura l'esdeveniment.
  - Tenir connexió a internet.
  >
  > A "Preferencias" es podran canviar els gustos que prèviament s'han indicat o posar els primers gustos si la primera vegada de fer login va fer clic sobre "omitir".
  
## Feed d'esdeveniments
  Hi ha 3 pestanyes "Tabs": Todos, Asistiré i Recomendados.
  >
  > En cada una d'aquestes pestanyes et mostren esdeveniments d'eventful. Es poden canviar fent clic sobre qualsevols dels titols de les tabs o fent swipe lateralment sobre la pantalla. Fent swipe cap abaix estant a dalt de tot dels llistats es pot actualitzar els events (si fos necessari).
  >
  > A "Todos" es mostren absolutament tots els esdeveniments sense aplicar cap tipus de gusto definit per els usuaris. Estan ordenats per relevància, es poden veure els número d'assitents de la nostre aplicació i el nombre de takes que obtindries si es fes el check-in.
  >
  > A "Asistiré" apareixen tots els esdeveniments on l'usuari hagi indicat que vol assistir i així poder-li evitar feina de cercar pels que volia anar.
  >
  > A "Recomendados" sortiran esdeveniments que s'satisfan els seus gustos (ciutat i/o categories), si l'usuari no ha definit cap gusto, es mostraran tots els esdeveniments.
  >
  > A la fitxa de totes les pestanyes surt la informació de l'esdeveniment (Nom, ubicació, categoria, dia i hora), també una imatge que fa referencia a l'esdeveniment, el nombre d'assistents i takes. Si l'API d'eventful ens retorna una imatge per l'esdeveniment és la que s'utilitza per mostrar a l'usuari, en cas que no ens la facilitin es posa una imatge que fa referencia a la categoria. Totes les categories tenen una imatge associada.
  
## Mi perfil
  > Pantalla molt informativa amb 0 navegabilitat. Es mostra el nom d'usuari, nom de la persona, els takes i 
  > events totals, el nivell actual i el següent i la barra d'experiencia.
  >
  > Sota aquesta informació es podran veure els "logros" que s'han aconseguit (En color rosat els logros aconseguits,
  > en gris els encara no realitzats).
  >
  > Hi ha un botó circular a prop de la cantonada inferior dreta que permet amb un únic clic accedir a la
  > pantalla d'intercanviar takes.
