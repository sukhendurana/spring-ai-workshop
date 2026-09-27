package dev.sukhendu.springai.workshop.controller.structuredoutput;

import com.openai.models.chat.completions.StructuredChatCompletion;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/songs")
public class SongsController{
    ChatClient chatClient;

    public SongsController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/find")
    public Album findSongs(@RequestParam(value = "artist", defaultValue = "AR Rahman") String artist) {

        return chatClient.prompt()
                .user(e -> {
                    e.text("Find top 5 popular songs by {artist} using symphony in their songs")
                            .param("artist", artist);
                })
                .call()
                .entity(Album.class);
    }
}
