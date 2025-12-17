package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class WhisperSttCli {

  // Whisper 용 URL 고정!
  private static final String OPENAI_URL = "https://api.openai.com/v1/audio/transcriptions";

  public static void main(String[] args) throws Exception {
    if (args.length < 1) {
      System.out.println("Usage: java -jar stt-cli.jar <audio-file-path>");
      return;
    }

    String apiKey = System.getenv("OPENAI_API_KEY");
    if (apiKey == null || apiKey.isBlank()) {
      throw new IllegalStateException("OPENAI_API_KEY env is missing");
    }

    File audio = new File(args[0]);
    if (!audio.exists()) throw new IllegalArgumentException("Audio file not found: " + audio.getAbsolutePath());


    // 우선 데모 안정성을 위해 mp3(mpeg) 로 고정
    RequestBody fileBody = RequestBody.create(MediaType.parse("audio/mpeg"), audio);


    RequestBody multipart = new MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart("model", "gpt-4o-transcribe-diarize")
        .addFormDataPart("response_format", "diarized_json")
        .addFormDataPart("chunking_strategy", "auto") // 30초 이상 필수 :contentReference[oaicite:5]{index=5}
        .addFormDataPart("file", audio.getName(), fileBody)
        .build();

    Request req = new Request.Builder()
        .url("https://api.openai.com/v1/audio/transcriptions")
        .addHeader("Authorization", "Bearer " + System.getenv("OPENAI_API_KEY"))
        .post(multipart)
        .build();

    OkHttpClient client = new OkHttpClient.Builder()
        .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
        .writeTimeout(5, java.util.concurrent.TimeUnit.MINUTES)
        .readTimeout(15, java.util.concurrent.TimeUnit.MINUTES)
        .callTimeout(15, java.util.concurrent.TimeUnit.MINUTES)
        .retryOnConnectionFailure(true)
        .build();

    ObjectMapper om = new ObjectMapper();

    try (Response response = client.newCall(req).execute()) {
      String body = response.body().string();
      JsonNode root = om.readTree(body);
      if (!response.isSuccessful()) {
        throw new RuntimeException("OpenAI STT failed: " + response.code() + "\n" + body);
      }


      // diarized_json은 segments에 speaker/start/end/text가 들어오는 형태
      JsonNode segments = root.get("segments");
      if (segments == null || !segments.isArray()) {
        throw new IllegalStateException("No segments in diarized response");
      }

      System.out.println("=== Diarized Segments ===");
      for (JsonNode s : segments) {
        String speaker = s.path("speaker").asText("unknown");
        double start = s.path("start").asDouble();
        double end = s.path("end").asDouble();
        String text = s.path("text").asText();
        System.out.printf("[%s][%.2f - %.2f] %s%n", speaker, start, end, text);
      }

      // 원본 전체 저장
      Files.writeString(Path.of("diarized_raw.json"),
          om.writerWithDefaultPrettyPrinter().writeValueAsString(root));
      System.out.println("\nSaved: diarized_raw.json");

    }
  }
}
