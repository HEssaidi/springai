package com.example.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final ChatClient chatClient;

    public AiService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String ask(String question) {
//        prompt() = Prepare the request.
//        system() = Define how the AI should behave - role or instructions.
//        user() = Provide the question.
//        call() = Send the request
//        and wait.content() = Get the AI's text response.
//        return chatClient.prompt()
//                .system("You are a Java programming tutor.")
//                .user(question)
//                .call()
//                .content();

//        call() – Synchronous response
//        - Sends the request to Gemini.
//        - Waits until Gemini generates the complete response.
//        - Returns the result all at once.
//        stream() – Streaming response
//        - Sends the request to Gemini.
//        - Receives the response progressively, in chunks.
//                - Allows you to display text as it is generated.

        ChatResponse response = chatClient.prompt()
                .system("You are a Java programming tutor.")
                .user(question)
                .call()
                .chatResponse();

        Usage usage = response.getMetadata().getUsage();
        String answer = response.getResult().getOutput().getText();
        System.out.println("Answer: " + answer);

        System.out.println("Prompt tokens: " +
                usage.getPromptTokens());

        System.out.println("Completion tokens: " +
                usage.getCompletionTokens());

        System.out.println("Total tokens: " +
                usage.getTotalTokens());

        return answer;
    }

    private void subsc(String question) {
        chatClient.prompt()
                .user(question)
                .stream()
                .content()
                .doOnNext(chunk -> System.out.println(chunk))
                .doOnComplete(() -> System.out.println("Response completed!"))
                .doOnError(error -> System.out.println("Error: " + error.getMessage()))
                .subscribe();
    }


    public record Car(
            String brand,
            String model,
            int year,
            String color
    ) {}


    public record Person(
            String name,
            int age,
            String profession
    ) {}


    public Person extractInfos(String text) {

        return chatClient.prompt()
                .user("Extract the person's information: " + text)
                .call()
                .entity(Person.class);
    }
}