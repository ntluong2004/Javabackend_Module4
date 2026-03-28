package com.tlu.learning.ex_full.chapter1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Chapter1Ex2Dictionary {

    private static final Map<String, String> dictionary = new HashMap<>();

    static {
        dictionary.put("hello", "xin chào");
        dictionary.put("apple", "quả táo");
        dictionary.put("banana", "quả chuối");
        dictionary.put("computer", "máy tính");
        dictionary.put("spring", "mùa xuân (hoặc Spring framework)");
    }

    @GetMapping("/dictionary")
    public ResponseEntity<String> translate(@RequestParam(value = "word", required = false) String word) {

        if (word == null || word.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Vui lòng nhập từ cần tra cứu!");
        }
        String processedWord = word.trim().toLowerCase();

        if (dictionary.containsKey(processedWord)) {
            String result = dictionary.get(processedWord);
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy từ này trong từ điển."); // Trả về 404
        }
    }
}