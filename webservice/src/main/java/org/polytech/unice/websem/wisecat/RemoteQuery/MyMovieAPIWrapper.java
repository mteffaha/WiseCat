package org.polytech.unice.websem.wisecat.RemoteQuery;

import org.json.JSONObject;
import org.polytech.unice.websem.wisecat.model.Movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.logging.Logger;

/**
 * Created by mtoffaha on 10/02/2015.
 */
public class MyMovieAPIWrapper {


    public static Movie populateMovie(Movie movie){

        if(movie.getTitle() == null){
            System.err.println("Null Titlt unable to proceed");
            return movie;
        }

        JSONObject movieJSON = null;
        try {
            URL mymovieAPIURL = new URL("http://www.myapifilms.com/imdb?title="+ URLEncoder.encode(movie.getTitle()) +"&format=JSON&aka=0&business=0&seasons=0&seasonYear=0&technical=0&filter=N&exactFilter=0&limit=1&lang=en-us&actors=N&biography=0&trailer=1&uniqueName=0&filmography=0&bornDied=0&starSign=0&actorActress=0&actorTrivia=0&movieTrivia=0&awards=0&moviePhotos=N&movieVideos=N");
            HttpURLConnection conn = (HttpURLConnection) mymovieAPIURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line+"\n");
            }
            movieJSON = new JSONObject(response);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(movieJSON.get("plot"));

        return movie;
    }
}
