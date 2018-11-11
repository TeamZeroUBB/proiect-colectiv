
	Proiectul e structurat folosind principiul de MVC. Mai precis flowul ar trebui sa vina: Controller -> Facade -> Service -> Dao -> Model(?)

	Requirements:
		1. Descarcativa MySql si creati o baza de date noi cu numele appdb, cu username si parola: root  (vedeti fisierul de proprietati din resources -> application.proprieties)
		
	
	Ca sa compilati proiectul:
		1. Verificati daca aveti ca si variabila de enviroment java (Enviroment Variables JAVA_HOME)
			daca nu aveti: https://www.mkyong.com/java/how-to-set-java_home-on-windows-10/
		2. Verificati daca aveti instalat maven si setat ca si variabila de Enviroment\
			daca nu: https://www.mkyong.com/maven/how-to-install-maven-in-windows/
		
		3. Compilare Maven:
			3.1 - Deschideti o consola in acelasi fisier cu pom.xml
			3.2 - Scrieti: mvn clean install
			3.3 - va face magie
			3.4 - dupa ce termina intrati in folderul target si copiati App.war
		4. Tomcat
			4.1 - am pus tomcatul meu in proiect
			4.2 - intrati in:    apache-tomcat-x.x.x.x.x.x.x.   ->   webapps   
			4.3 - in folderul webapps dati paste la fisierul war
			4.4 - dati back si intrati in folderul bin
			4.5 - deschideti o consola acolo si scrieti:  catalina jpda run  (daca nu merge cu jpda puteti scrie doar:  catalina run)
		5. Aplicatia ar trebui sa ruleze pe:  http://localhost:8080/App/
		
		
	Current features:
		Happy flow:
			1. http://localhost:8080/App/
			2. http://localhost:8080/App/user/
			3. Creati un cont nou de Client
			4. Teoretic acum trebuie sa intrati pe adresa de email sa confirmati emailul, eu am proxy si nu am putut testa featur-ul asta, dar merge cand eram tanar fecior)
			   Daca nu reusiti puteti schimba in tabelul din baza de date din AWAITING_ACTIVATION in ACTIVE
			5. Acum ar trebui sa puteti accesa http://localhost:8080/App/user/
			6. http://localhost:8080/App/backoffice/
			7. http://localhost:8080/App/loginForm    ->   logout   ->    Register Backoffice
			8. Singurul mod ca sa fie aprobat este de a inlocui in tabelul din DB coloana de status din AWAITING_APPROVAL in APPROVED
			9. http://localhost:8080/App/backoffice/
			10.  http://localhost:8080/App/user/
			
		Daca vreti internationalizare puteti scrie in continuarea url-ului la pagina de login si register:   ?language=ro
		Pls folositi asta de la inceput daca o vom duce pana la capat, ca nu exista ceva mai oribil decat sa schimbi fiecare propozitie cu o variabila la final
		
		For more questions leave a like and hit that subscribe button!