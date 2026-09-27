package dev.sukhendu.springai.workshop.controller.multimodal;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image-detection")
public class ImageDetection {

    ChatClient chatClient;

    public ImageDetection(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @Value("classpath:/images/image.jpg")
    Resource image;

    @GetMapping("/detect")
    public String detectImage() {
        return chatClient.prompt()
                .user(e -> {
                    e.text("Detect the given image and describe it in 200 words");
                    e.media(MimeTypeUtils.parseMimeType("image/webp"), image);
                })
                .call()
                .content();
    }


}
