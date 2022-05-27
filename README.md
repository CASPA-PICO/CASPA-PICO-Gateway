<h1>CASPA-PICO Gateway</h1>
<p>Le programme est un projet <a href="https://github.com/gradle/gradle">Gradle</a>, développé en Java version 17.</p>
<h2>Bibliothèques utilisées</h2>
<p>
  <ul>
    <li><a href="https://github.com/spring-projects/spring-boot">Spring Boot</a></li>
    <li><a href="https://github.com/spring-cloud/spring-cloud-netflix">Spring Cloud Netflix</a></li>
  </ul>
</p>
<h2>Fonctionnement global du programme</h2>
<p>Ce programme sert de <a href="https://fr.wikipedia.org/wiki/Proxy_inverse"><b>proxy inverse</b></a>, en effet sur la machine virtuelle seuls les ports 443 (HTTPS) et 80 (HTTP) sont ouverts au publique. La Gateway permet de rediriger les requêtes en fonction du chemin de l'URL vers différents serveurs web.<br/>
Dans notre cas, les requêtes vers https://caspa.icare.univ-lille.fr/grafana sont redirigées vers le serveur web de Grafana (port 3000). Les autres requêtes sont toutes redirigées vers le serveur web CASPA-PICO (port 8080).<br/></p>
<h2>Configuration du programme</h2>
<h3>Configuration des redirections</h3>
<p>
  Pour changer la configuration du programme, modifiez le fichier <a href="https://github.com/CASPA-PICO/CASPA-PICO-Gateway/blob/master/application_dev.yml"><b>application_dev.yml</b></a>.</br>
Vous pouvez modifier les redirections en changeant les champs "path" et "url".</br></br>
Exemple :</br>
<ul>
  <li>Redirection de toutes les requêtes de "/grafana/" vers le serveur web de grafana (tournant sur le port 3000 en local avec le sufix /grafana) :
    <ul>
      <li><b>path</b> : /grafana/**</li>
      <li><b>url</b> : http://localhost:3000/grafana</li>
    </ul>
  </li>
  <li>Redirection de toutes les autres requêtes vers le serveur web CASPA-PICO (tournant sur le port 8080 en local) :
    <ul>
      <li><b>path</b> : /**</li>
      <li><b>url</b> : http://localhost:8080</li>
    </ul>
  </li>
</ul>
<b>Note</b> : la connexion n'a pas besoin d'être sécurisée entre la Gateway et les serveurs locaux (utilisation de http:// dans les url ci-dessus), cependant elle est sécurisée entre l'utilisateur et la Gateway<br/></p>
<h3>Configuration de la sécurisation de la connexion (HTTPS)</h3>
<p>
  Pour sécuriser la connexion entre l'utilisateur et la Gateway, il est nécessaire d'obtenir un certificat SSL pour le nom de domaine caspa.icare.univ-lille.fr.<br/>
Pour cela vous pouvez utiliser un service comme <a href="https://www.sslforfree.com/">SSL For Free</a>.<br/>
Une fois le certificat obtenu, il doit être mis au format .p12 pour pouvoir être utilisé par la Gateway.<br/>
  Vous pouvez suivre <a href="https://devstory.net/12885/installer-un-certificat-ssl-gratuit-lets-encrypt-pour-spring-boot">ce guide</a> qui explique comment obtenir un fichier .p12</br></br>
Une fois le fichier obtenu, il faut modifier la configuration de la Gateway, toujours dans le fichier .yml.</br>
Modifiez les lignes suivantes sous <b>ssl</b> :<br/>
<ul>
  <li><b>enabled</b> : true</li>
  <li><b>key-store</b> : chemin vers le fichier .p12
  <li><b>key-store-password</b> : mot de passe du store (choisi lors de sa création)</li>
  <li><b>key-alias</b> : alias du store (choisi lors de sa création avec l'argument -name)</li>
</ul>
<b>Modifiez le port de la gateway</b> : sous "server", changer "port" à 443.
</p>
<h2>Compilation du programme</h2>
<p>
  Pour compiler le programme, assurez vous d'avoir installer la version 17 du JDK.
  <h3>Sous Windows</h3>
  <ol>
    <li>Cloner le repo Github et ouvrir un terminal à la racine du projet</li>
    <li>Compiler le programme avec : <i>./gradlew.bat assemble</i></li>
    <li>Le .jar compilé se trouve dans le dossier build/libs/ et est nommé <b>CASPA-PICO-Gateway-0.0.1-SNAPSHOT.jar</b>
  </ol>
  <h3>Sous Linux</h3>
  <ol>
    <li>Cloner le repo Github et ouvrir un terminal à la racine du projet</li>
    <li>Compiler le programme avec : <i>./gradlew assemble</i></li>
    <li>Le .jar compilé se trouve dans le dossier build/libs/ et est nommé <b>CASPA-PICO-Gateway-0.0.1-SNAPSHOT.jar</b>
  </ol>
</p>
<h2>Lancement du programme</h2>
<p>
  Regroupez dans un même dossier le fichier .jar et votre fichier de configuration application_dev.yml</br>
  <h3>Pour Windows</h3>
  <ol>
    <li>Ouvrir un terminal dans le dossier contenant le .jar et le .yml</li>
    <li>Lancer la Gateway avec la commande :<br/><i>java -jar CASPA-PICO-Gateway-0.0.1-SNAPSHOT.jar -Dspring.config.location=application_dev.yml</i></li>
  </ol>
  <h3>Pour Linux</h3>
  <p>Le port 443 (HTTPS) ne peut être utilisé que par les utilisateurs root, pour contourner cela et ne pas avoir à lancer la Gateway en root il est possible d'ajouter une exception.</br></p>
  <ol>
    <li>Ouvrir un terminal dans le dossier contenant le .jar et le .yml</li>
    <li>Installer authbind : <i>sudo apt get update && sudo apt get install authbind</i>
    <li>Autoriser l'utilisation du port 443 :<br/><i>sudo touch /etc/authbind/byport/443</i> puis <i>sudo chmod 777 /etc/authbind/byport/443</i>
    <li>Lancer la Gateway avec la commande :<br/><i>authbind -Djava.net.preferIPv4Stack=true --deep java -jar CASPA-PICO-Gateway-0.0.1-SNAPSHOT.jar -Dspring.config.location=application_dev.yml</i></li>
  </ol>
</p>
