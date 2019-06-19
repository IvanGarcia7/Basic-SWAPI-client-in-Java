package es.uma.rysd.app;

import javax.net.ssl.HttpsURLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.google.gson.Gson;

import es.uma.rysd.entities.SearchResponse;
import es.uma.rysd.entities.Planet;
import es.uma.rysd.entities.People;
import es.uma.rysd.entities.Film;

public class SWClient {
	
    private final String app_name = "Mi aplicaciÛn de Star Wars";

    private final String url_api = "https://swapi.co/api/";
    private final String people = "people/";
    private final String film = "film/";
    
    
    
    
    
       
    public People search(String name) throws UnsupportedEncodingException {
    	People p = null;
    	
    	
    	URL urlcreada = null;
		try {
			urlcreada = new URL(url_api+people+"?search="+URLEncoder.encode(name, "UTF-8"));
		
		} catch (MalformedURLException e) {
			
			System.err.println("Error al crear la url");
			
		}
		
		
		
		
		
    	// TODO: Cree la conexiÛn a partir de la URL (url_api + name tratado - vea el enunciado)
		HttpsURLConnection conexion = null;
		try {
			
			conexion = (HttpsURLConnection) urlcreada.openConnection();
			//System.out.println(conexion.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error al conectar");
			
		}
		
		
		
		
		
    	// TODO: AÒada las cabeceras User-Agent y Accept (veael enunciado)
    	
    	conexion.setRequestProperty("User-Agent", app_name);
    	conexion.setRequestProperty("Accept", "application/json");
    	// TODO: Indique que es una peticiÛn GET
    	try {
			conexion.setRequestMethod("GET");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			System.err.println("Error metodo GET");
			
		}
    	// TODO: Compruebe que el cÛdigo recibido en la respuesta es correcto
    	int codigo = 0;
		try {
			codigo = conexion.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error el codigo recibido no es correcto");
			
		}
    	
		
		
    	People resultado = null;
		if(codigo/100 != 2) {
    		//Envio un codigo de error
    		System.out.println("Comando no v·lido, error 2XX");
    	}else {
    		
    		// TODO: Deserialice la respuesta a SearchResponse
            Gson parser = new Gson();
            InputStream in = null;
            SearchResponse lp = null;
            
			try {
				in = conexion.getInputStream();
				//System.out.println(in.toString());
	            lp = parser.fromJson(new InputStreamReader(in), SearchResponse.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Error al obtener el imput Stream");
				
			}// TODO: Obtenga el InputStream de la conexiÛn
			
			
			
			
			
			try {
				resultado = lp.results[0];
			}catch(ArrayIndexOutOfBoundsException ie) {
				return null;
			}
			
			
			
			
			//Tengo que crear un array de pelis
			
			
			
			/*
			
			System.out.println("Personaje encontrado en la base de datos:");
			
		    
		    */
            
        	// TODO: Obtenga los datos de las pelis del personaje (se recomienda hacer un mÈtodo)
        		Film [] pelis =obtenerDatosPelicula(resultado.films);
        		resultado.movies = pelis;
			
        		
        		resultado.homeplanet=obtenerDatosPlaneta(resultado.homeworld);
        		
        	// TODO: Obtenga la informaciÛn sobre el planeta natal del personaje (se recomienda hacer un mÈtodo)
        	
    		
    	}
    	
    	return resultado;
    }






	private Planet obtenerDatosPlaneta(String homeworld) {
		// TODO Auto-generated method stub
		
		 Planet lp = new Planet();
		// TODO: Cree la URL correspondiente
    	URL urlcreada = null;
		try {
			urlcreada = new URL(homeworld);
			//System.out.println(urlcreada);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error al crear la url");
		}
		
		
		
		
		
		
    	// TODO: Cree la conexiÛn a partir de la URL (url_api + name tratado - vea el enunciado)
		HttpsURLConnection conexion = null;
		try {
			
			conexion = (HttpsURLConnection) urlcreada.openConnection();
			//System.out.println(conexion.toString());
		} catch (IOException ie) {
			// TODO Auto-generated catch block
			System.err.println("Error al conectar");
			
		}
		
		
		
		
		
    	// TODO: AÒada las cabeceras User-Agent y Accept (veael enunciado)
    	
    	conexion.setRequestProperty("User-Agent", app_name);
    	conexion.setRequestProperty("Accept", "application/json");
    	// TODO: Indique que es una peticiÛn GET
    	try {
			conexion.setRequestMethod("GET");
		} catch (ProtocolException ie) {
			// TODO Auto-generated catch block
			System.err.println("Error metodo GET");
			
		}
    	// TODO: Compruebe que el cÛdigo recibido en la respuesta es correcto
    	int codigo = 0;
		try {
			codigo = conexion.getResponseCode();
		} catch (IOException ie) {
			// TODO Auto-generated catch block
			System.err.println("Error el codigo recibido no es correcto");
			
		}
    	
		
		
		if(codigo/100 != 2) {
    		//Envio un codigo de error
    		System.out.println("Comando no v·lido, error 2XX");
    	}else {
    		
    		// TODO: Deserialice la respuesta a SearchResponse
            Gson parser = new Gson();
            InputStream in = null;
            lp = new Planet();
            
			try {
				in = conexion.getInputStream();
				//System.out.println(in.toString());
	            lp = parser.fromJson(new InputStreamReader(in), Planet.class);
			} catch (IOException ie) {
				// TODO Auto-generated catch block
				System.err.println("Error al obtener el imput Stream");
				
			}
		
			
			
			
    	}
		
		
		

		return lp;
	}






	private Film[] obtenerDatosPelicula(String[] films) {
		// TODO Auto-generated method stub
		
		Film [] resultado = new Film [films.length];
		
		
		for(int i=0;i<films.length;i++) {
		
		
		
    	
    	// TODO: Cree la URL correspondiente
    	URL urlcreada = null;
		try {
			urlcreada = new URL(films[i]);
			//System.out.println(urlcreada);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error al crear la url");
		}
		
		
		
		
		
		
    	// TODO: Cree la conexiÛn a partir de la URL (url_api + name tratado - vea el enunciado)
		HttpsURLConnection conexion = null;
		try {
			
			conexion = (HttpsURLConnection) urlcreada.openConnection();
			//System.out.println(conexion.toString());
		} catch (IOException ie) {
			// TODO Auto-generated catch block
			System.err.println("Error al conectar");
			
		}
		
		
		
		
		
    	// TODO: AÒada las cabeceras User-Agent y Accept (veael enunciado)
    	
    	conexion.setRequestProperty("User-Agent", app_name);
    	conexion.setRequestProperty("Accept", "application/json");
    	// TODO: Indique que es una peticiÛn GET
    	try {
			conexion.setRequestMethod("GET");
		} catch (ProtocolException ie) {
			// TODO Auto-generated catch block
			System.err.println("Error metodo GET");
			
		}
    	// TODO: Compruebe que el cÛdigo recibido en la respuesta es correcto
    	int codigo = 0;
		try {
			codigo = conexion.getResponseCode();
		} catch (IOException ie) {
			// TODO Auto-generated catch block
			System.err.println("Error el codigo recibido no es correcto");
			
		}
    	
		if(codigo/100 != 2) {
    		//Envio un codigo de error
    		System.out.println("Comando no v·lido, error 2XX");
    	}else {
    		
    		// TODO: Deserialice la respuesta a SearchResponse
            Gson parser = new Gson();
            InputStream in = null;
            Film lp = new Film();
            
			try {
				in = conexion.getInputStream();
				//System.out.println(in.toString());
	            lp = parser.fromJson(new InputStreamReader(in), Film.class);
			} catch (IOException ie) {
				// TODO Auto-generated catch block
				System.err.println("Error al obtener el imput Stream");
				
			}
		
			
			resultado[i]=lp;
			
    	}
		
		
	}

		
	
		return resultado;
	}



	






	
}
            
          
