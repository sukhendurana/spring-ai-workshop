package dev.sukhendu.springai.workshop.controller.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    ChatClient chatClient;

    public ArticleController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/new")
    public String generateArticle(@RequestParam(value = "topic", defaultValue = "AI in Banking") String topic) {

        var system = """
                Blog Post generator guidelines:
                
                1. Length and Purpose: Generate 500 word blog post that inform and engage general audience.
                2. Structure:
                     - Introduction: Start with a hook to grab the reader's attention and introduce the topic.
                      - Body: Provide detailed information, insights, and examples related to the topic. Use subheadings for clarity.
                      - Conclusion: Summarize key points and provide a call to action or final thought.
                3. Style and Tone: Use a conversational and approachable tone. Avoid jargon and technical terms unless necessary, and explain them if used.
                4. SEO Considerations: Include relevant keywords naturally throughout the content. Use meta descriptions and headings to enhance search engine visibility.
                5. Originality: Ensure the content is unique and not copied from other sources. Provide fresh perspectives and insights.
                6. Visuals: Suggest relevant images, infographics, or videos to complement the text. Describe the visuals in the content and provide alt text for accessibility.
                7. References: If applicable, cite credible sources to support claims and provide additional reading material. Use proper citation formats.
                8. Editing and Proofreading: Review the content for grammar, spelling, and punctuation errors. Ensure clarity and coherence in the writing.
                9. Call to Action: Encourage readers to engage with the content, share their thoughts, or take specific actions related to the topic.
                10. Compliance: Ensure the content adheres to ethical guidelines, copyright laws, and any relevant industry regulations.
                
                """;

        return chatClient.prompt()
                .system(system)
                .user(e -> {
                    e.text("Write a blog post about {topic}")
                            .param("topic", topic);
                })
                .call()
                .content();
    }
}
