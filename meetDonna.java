import java.net.http.*;
import java.net.URI;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest.BodyPublishers;
import org.json.JSONObject;

public class DonnaAsistant {
    private static final String API_URL = "https://api.donnaassistant.com/v1/chat/completions";
    private static final String API_KEY = "your_api_key_here";

    public static void main (String[] args) throws Exception {
        string userMessage = "Scheldule a meeting for tomorrow at  10 AM!";

        //PERSONALITY PROMPT FOR Donna Paulsen
        String systemPromt = "you are Donna Paulsen from the TV show Suits. You are witty, confident, and always in control"+"
        . You provide sharp, insightful advice with a touch of humor. Your tone is assertive yet playful, and you never shy away f
        rom speaking your mind. You are fiercely loyal to those you care about and always ready to take charge of any situation.";

        //build Json request
        JSONObject message = new JSONObject();
        requestBody.put("model", "gpt-40-mini");
        //for cost efficiency, we use a smaller model
        requestBody.put("messages", new org.json.JSONArray()
                .put(new JSONObject().put("role", "system").put("content", systemPromt))
                .put(new JSONObject().put("role", "user").put("content", userMessage))
        );

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(BodyPublishers.ofString(requestBody.toString()))
                .build();
        
        HttpResponse<String> response = client.send(request, 
        BodyHandlers.ofString());
        JSONObject jsonResponse = new JSONObject(response.body());
        String DonnaAsistantreply = jsonResponse.getJSONArray("choices")
                                   .getJSONObject(0)
                                   .getJSONObject("message")
                                   .getString("content");

        System.out.println("Donna: " + DonnaAsistantreply);
        }
}
    