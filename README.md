<h1>CASPA-PICO Gateway</h1>
<p>Le programme est un projet Gradle, développé en Java version 17.</p>
<h2>Bibliothèques utilisées</h2>
<ul>
  <li><a href="https://github.com/spring-projects/spring-boot">Spring Boot</a></li>
  <li><a href="https://github.com/spring-cloud/spring-cloud-netflix">Spring Cloud Netflix</a></li>
</ul>
<h2>Fonctionnement global du programme</h2>
Ce programme sert de <a href="https://fr.wikipedia.org/wiki/Proxy_inverse"><b>proxy inverse</b></a>, en effet sur la machine virtuelle seuls les ports 443 (HTTPS) et 80 (HTTP) sont ouverts au publique. La Gateway permet de rediriger les requêtes en fonction du chemin de l'URL vers différents serveurs web.<br/>
Dans notre cas, les requêtes vers https://caspa.icare.univ-lille.fr/grafana sont redirigées vers le serveur web de Grafana (port 3000). Les autres requêtes sont toutes redirigées vers le serveur web CASPA-PICO (port 8080).<br/>
