package com.example.springai.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProductTools {

    private final Map<String, Integer> products = Map.of(
            "laptop", 10,
            "mouse", 25,
            "keyboard", 0
    );

    @Tool(description = "Get the available stock quantity of a product by its name")
    public String getProductStock(@ToolParam(description = "The product name") String productName) {

        Integer stock = products.get(productName.toLowerCase());

        if (stock == null) {
            return "Product not found";
        }

//        User asks a question
//        Spring AI's Tool Call Advisor sends available tool definitions with their original schemas to the LLM
//        LLM analyzes the request, decides which tool to call, and returns a tool call request with required arguments
//        Tool Management dispatches and invokes the actual tool
//        Result is sent back to the LLM
//        LLM generates the final response incorporating the tool's result

        return productName + " has " + stock + " units available";
    }
}