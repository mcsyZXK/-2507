package com.zxk.springbootstart.controller;

import com.zxk.springbootstart.pojo.Result;
import com.zxk.springbootstart.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/announcement")
@CrossOrigin
@RequiredArgsConstructor
public class AnnouncementController {
    private final AnnouncementService announcementService;
    private final ExecutorService virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();

    @GetMapping("/getAll")
    public Result getAll() {
        return Result.success(announcementService.GetAll());
    }

    @PostMapping("/like")
    public Result handleLike(@RequestBody Map<String, Object> request) {
        try {
            Integer id = Integer.parseInt(request.get("id").toString());
            Long count = Long.parseLong(request.get("count").toString());

            Long newTotal = announcementService.updateRedis(id, count);
            virtualThreadExecutor.execute(() -> announcementService.increment(id, count));

            return Result.success(Map.of(
                    "totalLikes", newTotal,
                    "message", "Like processed successfully"
            ));
        } catch (Exception e) {
            return Result.error("Invalid request: " + e.getMessage());
        }
    }
}