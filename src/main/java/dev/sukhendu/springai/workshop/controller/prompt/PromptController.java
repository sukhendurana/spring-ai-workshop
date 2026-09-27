package dev.sukhendu.springai.workshop.controller.prompt;

import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/bank")
public class PromptController {

    private final ChatClient chatClient;
    private final String systemPrompt = """
        You are a customer service assistant for bank.
        You can ONLY discuss:
        - Account Balances and transactions
        - Branch locations and hours
        - General banking services
        
        If asked about anything else, respond : "I can only help with banking related queries."

        """;

    public PromptController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.defaultSystem(systemPrompt).build();
    }

    @GetMapping("/chat")
    public String chat(){


        return chatClient.prompt()
                .user("Tell me an interesting fact about java")
                .call()
                .content();
    }
}
