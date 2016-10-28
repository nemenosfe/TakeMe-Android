# TakeMe-Android

## Pantalles
  - Login (LoginActivity.java)
  - Gustos (PreferencesActivity.java)
  - Drawer
  - EventFeed (EventsViewPagerFragment.java | TotsEventsFragment.java | RecomenatsFragment.java | AsistireFragment.java)
  - Profile (ProfileFragment.java)
  
## Accions per Pantalla
### Login
  > Twitter i Google+ : Encara no son operatives fins que no es fagi Oauth.
  >
  > Facebook : Utilitzat per poder avançar en el flow d'activitats

## Gustos
  > Toolbar : A falta d'estil final només tindrà un títol informant del nom de la app.
  >
  > Gustos principales: Servirà per poder conéixer els gustos dels 
  > nostres usuaris i poder recomanarlis els esdeveniments que més els hi poden interesar,
  > Es pdoen fer clic a més d'un checkbox.
  >
  > Ubicació: De la mateixa forma que amb els gustos es un dropdown que s'utilitza per filtrar
  > els esdeveniments que volem mostrar a l'usuari (el dropdown es el propi text "Todas las ciudades"
  >
  > Horari: Volem conéixer les hores en les que l'usuari podria assistir a un event i no mostrar-li
  > esdeveniments que no podria anar. (Són 2 switch per definir els dies: entre setmana o cap de setmana.
  > I pels horaris son dos TimePickers que s'obren al fer clic sobre les hores predefinides.
  >
  > Hi ha 2 botons: (Els dos envien al feed d'esdeveniments)
  >
  > "Omitir" -> Permet "ignorar" aquesta pantalla i no definir gustos.
  >
  > "Guardar" -> Guarda els gustos dels usuaris, com no son camps obligatoris podria fer-se clic a "Guardar" 
  > i no es guardaria cap.
  
## Drawer
  > Drawer de 4 opcions (Buscar eventos, Mi perfil, Cambiar Takes, Mis eventos), les dues últimes no han
  > entrat a la iteració.
  >
  > Amb "Buscar eventos" s'accedira directament al feed d'esdeveniments (directament sobre "Todos" més abaix explicat)
  > 
  > "Mi perfil" permet accedir al perfil de l'usuari a la mateixa pantalla trobarà els seus logros.
  >
  > "Cambiar takes": Servirà per intercanviar els "takes" la moneda de bescanvi utilitzada en aquesta app que 
  > s'aconsegueix assitint a events.
  >
  > Amb "Mis eventos" es podrà veure un historial dels events de l'usuari.
  
## Feed d'esdeveniments
  > Hi ha 3 pestanyes "Tabs": Todos, Asistiré i Recomendados.
  >
  > En cada una d'aquestes pestanyes et mostren els esdeveniments de l'usuari. Es poden canviar fent clic sobre
  > qualsevols dels titols de les tabs o fent swipe lateralment sobre la pantalla.
  >
  > A "Todos" es mostren absolutament tots els esdeveniments sense aplicar cap tipus de gusto definit per els usuaris.
  >
  > A "Asistiré" tots els eventos on l'usuari hagi indicat que vol asistir a l'esdeveniment i així
  > poder-li evitar feina de cercar per els que volia anar.
  >
  > A "Recomendados" sortiran esdeveniments on no hagi indicat que assitirà i es mostren els únics 
  > que coincideixin amb els gustos definits per l'usuari.
  >
  > A la fitxa de totes les pestanyes surt la informació de l'esdeveniment, a més a més un botó que et permetrà
  > compartir a qualsevol de les xarxes socials que es pot fer login (Facebook, Twitter o Google+), també 
  > es veurà la informació d'asistents dels usuaris de la app i els takes que et donaria assitir-hi.
  
## Mi perfil
  > Pantalla molt informativa amb 0 navegabilitat. Es mostra el nom d'usuari, nom de la persona, els takes i 
  > events totals, el nivell actual i el següent i la barra d'experiencia.
  >
  > Sota aquesta informació es podran veure els "logros" que s'han aconseguit (En color rosat els logros aconseguits,
  > en gris els encara no realitzats).
  >
  > Hi ha un botó circular a prop de la cantonada inferior dreta que permet amb un únic clic accedir a la
  > pantalla d'intercanviar takes.
