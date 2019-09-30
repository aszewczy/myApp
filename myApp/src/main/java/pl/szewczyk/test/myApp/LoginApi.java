package pl.szewczyk.test.myApp;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginApi {
	
	
	@RequestMapping("/logIn")
	public String login(@RequestBody User user) {
		
		long currentTimeMillis = System.currentTimeMillis();
		
		return Jwts.builder()     //definiuje co ma sie zawierac w tokenie
			.setSubject(user.getLogin())  //kto jest uzytkownikiem
			.claim("roles","user")  //claim przyjmuje elementy klucz wartość, czesto rola użytkownika sie znajduje
			.setIssuedAt(new Date(currentTimeMillis))   //kiedy token zaczyna wazność - aktualny czas 
			.setExpiration(new Date(currentTimeMillis + 20000))  //ustawienie czasu po którym token ma sie wygasić
			.signWith(SignatureAlgorithm.HS512, user.getPassword())     //hasło z argumentem haszującym 
			.compact(); //zwraca wynik w postaci  Stringa	
	}

}
