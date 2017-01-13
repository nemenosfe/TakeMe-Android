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
  > Gustos i ubicació: Els gustos de l'usuari es mostraran en una ListView, que s'omplirà en funció del que trïi l'usuari al dialog que sobre al fer clic sobre el botó que posa **"Seleccionar categorías"**, de la mateixa forma amb les ciutats d'interes. Les categories són totes les que eventful ens permet utilitzar, però per les ciutats son les 9 ciutats més poblades d'Espanya.
  >
  > No es guarden els gustos fins que no es selecciona el boto **"Guardar"**, el botó **"OK"** del dialog només permetre seleccionar els gustos que enviariem a la API.
  
## Drawer
  > Drawer de 6 opcions (Buscar eventos, Mi perfil, Cambiar Takes, Mis eventos, Preferencias y Desconectar).
  >
  > **"Buscar eventos"** ens permet accedir als 3 feeds d'esdeveniments que compte la nostra aplicació (Todos|Asistiré|Recomendados)
  >
  > **"Mi perfil"** permet accedir al perfil de l'usuari, a la mateixa pantalla trobarà els seus logros i les recompenses que ha obtingut.
  >
  > **"Cambiar takes"**: Servirà per intercanviar els "takes" la moneda de bescanvi utilitzada en aquesta app que s'aconsegueix assistint a esdeveniments i aconseguint logros.
  >
  > Amb **"Mis eventos"** es podrà veure un historial dels esdeveniments de l'usuari, aquest historial en situacions normals i sense la utilització del "GOD mode, que més tard s'explicarà", només mostrarà esdeveniments passats que s'ha indicat que s'assistiria i esdeveniments presents que ja s'ha fet un check-in. L'altre pestanya que té aquesta pantalla és on es fan els check-in, aquí només sortiran esdeveniments presents que no s'ha fet check-in i també esdeveniments futurs. En el moment que es fa un check-in es refresquen les dues pestanyes.
  > Per poder fer un check-in és necessari:
  - Trobar-se a **500 metres** del punt de check-in dels esdeveniments.
  - Estar **dintre del rang d'hores** que dura l'esdeveniment.
  - Tenir connexió a **internet**.
  >
  > A "Preferencias" es podran canviar els gustos que prèviament s'han indicat o posar els primers gustos si la primera vegada de fer login va fer clic sobre "omitir".
  
## Feed d'esdeveniments
  Hi ha 3 pestanyes "Tabs": **Todos, Asistiré i Recomendados**.
  >
  > En cada una d'aquestes pestanyes et mostren esdeveniments d'eventful. Es poden canviar fent clic sobre qualsevols dels titols de les tabs o fent swipe lateralment sobre la pantalla. Fent swipe cap abaix estant a dalt de tot dels llistats es pot actualitzar els events (si fos necessari).
  >
  > A **"Todos"** es mostren absolutament tots els esdeveniments sense aplicar cap tipus de gusto definit per els usuaris. Estan ordenats per relevància, es poden veure els número d'assitents de la nostre aplicació i el nombre de takes que obtindries si es fes el check-in.
  >
  > A **"Asistiré"** apareixen tots els esdeveniments on l'usuari hagi indicat que vol assistir i així poder-li evitar feina de cercar pels que volia anar.
  >
  > A **"Recomendados"** sortiran esdeveniments que s'satisfan els seus gustos (ciutat i/o categories), si l'usuari no ha definit cap gusto, es mostraran tots els esdeveniments.
  >
  > A la fitxa de totes les pestanyes surt la informació de l'esdeveniment (Nom, ubicació, categoria, dia i hora), també una imatge que fa referencia a l'esdeveniment, el nombre d'assistents i takes. Si l'API d'eventful ens retorna una imatge per l'esdeveniment és la que s'utilitza per mostrar a l'usuari, en cas que no ens la facilitin es posa una imatge que fa referencia a la categoria. Totes les categories tenen una imatge associada.
  
## Evento
  > Es veu la informació completa dels esdeveniments, començant per una imatge que de la mateixa manera com al feed, si no es troba una imatge des de l'API de eventful es posa la imatge que representa la seva categoria. A la cantonada superior dreta de la imatge apareix el número de takes dintre d'un cercle.
  >
  > A continuació es troben 3 botons, el primer es tracta d'un botó amb la icona d'un mapa i que si és pressiona obra una activitat on et mostra la ubicació de l'esdeveniment i també es pot veure la teva ubicació actual. A la dreta es pot trobar el botó de "share", aquest botó permet compartir per xarxes socials, correu o altres aplicacions de missatgeria l'esdeveniment que s'està consultant. I la resta de l'espai es troba el botó de "Assistiré/No-Assistiré" al fer clic en assistiré es marca com esdeveniment a assistir i s'actualitza el esdeveniment i el botó passa a la seva negació, si es fa clic en el moment que indica "No-Assistiré" es desmarca i és desactualitza. Important: No es podrà marcar "No-Assistir" a un esdeveniment si ja s'ha fet el check-in. Al assistir a un esdeveniment es passa automàticament al llistat d'esdeveniments que es poden fer check-in.
  >
  > Per sota dels botons estan els camps informatius de títol, hora i descripció, aquest últim camp podria no ser-hi si l'API de eventful no la retorna.
  
## Mi perfil
  > En aquesta pantalla es pot veure el número de takes de l'usuari, el nombre d'esdeveniments als quals s'ha fet check-in, amb el nom d'usuari i el seu nivell actual també representat per una barra d'experiència. A la segona part del perfil es troba els logros aconseguits i les recompenses obtingudes.
  >
  > **Logros:** Es veuen els logros que ha completat l'usuari, es pot veure el nom i la descripció del logro. Els logros que no s'han aconseguit, no hi ha forma de veurel's, així es fomenta la visita a esdeveniments.
  >
  > **Recompenses:** Es pot veure totes les recompenses que s'han aconseguit a través dels takes obtinguts, a part del nom de la recompensa i de la descripció també es pot veure el nombre de recompenses que has obtingut per cada un dels objectes.
  
## Market
  > El market està format per dos pantalles, la primera i la qual s'accedeix des del drawer o els floating buttons del perfil, es un llistat dels nivells de les recompenses que van des de 1 a 8. Depenen del nivell de l'usuari es poden bescanviar les recompenses asociades al nivell, sempre que el nivell de l'usuari sigui igual o superior apareixerà un candau de color verd-blavós, en canvi si el nivell de l'usuari es inferior, el candau sera de color gris i no sera clicable.
  >
  > Després de fer clic sobre un dels nivells s'entrarà en una pantalla amb un llistat de totes les recompenses que poden ser obtingudes amb takes. Les targetes de les recompenses compten del número de takes, el nom i una breu descripció, per cada una de les recompenses tenen associat un botó que en fer-hi clic, sobre un dialog de confirmació de compra, en acceptar-la se li descompten els takes i s'actualitzen el llistat de recompenses al perfil de l'usuari.
  >
  > A les dues pantalles hi ha el username, nivell actual i el nombre de takes de l'usuari.
  
## Check-In
  > Com en el perfil aquesta pantalla està formada per dues pestanyes, en aquest cas la primera és l'historial d'esdeveniments i la segona els esdeveniments que es poden check-in.
  >
  > En **l'historial** només es mostren els esdeveniments passats que s'han marcat com assistiré, de color vermell si no s'ha fet check-in o de color verd en cas contrari i el check-in s'ha realitzat correctament. També apareixeran els esdeveniments presents (s'estan realitzant en aquell moment) i que s'hagi fet un check-in vàlid.
  >
  > En el **check-in** apareixen els esdeveniments presents i que no s'ha fet un check-in vàlid encara. També apareixen els esdeveniments futurs on s'han indicat assistiré. El botó de check-in estarà de color vermell i desactivat si l'usuari no es troba a menys de 500 metres del punt de check-in de l'esdeveniment o si no es troba en l'hora correcte. Serà clicable i de color verd en el moment que compleixi les anteriors restriccions.
  >
  > A la que es faci un check-in vàlid l'esdeveniment en qüestió desapareixerà del llistat i passarà a l'historial.
  
# Aclaracions
  > Hi ha un **"GODMODE"** que permet forçar la distància i les hores per poder fer check-in, per poder fer check-in, ja que en ser esdeveniments reals és molt difícil que coincideixi amb un esdeveniment tan proper. Per poder accedir a aquest mode només cal prémer el nom d'usuari en el perfil, el username es modificarà i apareixerà un ```*``` al final, per deshabilitar-lo només és necessari fer el mateix procediment.
  > Totalment obligatori connectar-se des de la **universitat o amb VPN**.
  > Si no hi ha cap resultat a les RecyclerViews, es mostra un missatge que informa que no hi ha resultats.
