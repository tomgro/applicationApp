1. W projekcie użyłem bazy H2, liquibase oraz biblioteki PrimeFaces.
2. Przed uruchomieniem aplikacji proszę wykonać 
mvn liquibase:update 
w celu stworzenia pliku z bazą danych.
3. W pliku 'erd.png' znajduje się diagram bazy.
4. Wymaganie "Treść wniosku może się zmieniać tylko przy stanach 'CREATED' oraz 'VERIFIED'"
Przyjąłem, że treść wniosku może się zmienić, jeśli wniosek już jest w takim stanie.
5. Wyjątek podczas startu aplikacji wynika z błędu w PrimeFaces 5.1
http://stackoverflow.com/questions/26320170/java-io-notserializableexception-org-primefaces-component-datatable-datatable-a
6. Konsola H2 jest dostępna pod adresem http://localhost:PORT/core-app/console

