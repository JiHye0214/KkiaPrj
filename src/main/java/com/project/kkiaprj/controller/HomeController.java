package com.project.kkiaprj.controller;

import com.project.kkiaprj.domain.GamePlayer;
import com.project.kkiaprj.domain.GameSchedule;
import com.project.kkiaprj.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Controller
//@RestController
@RequestMapping("/")
public class HomeController {

    @Value("${app.api.mapKey}")
    private String mapKey;
    @Value("${app.api.weatherKey}")
    private String weatherKey;
    @Value("${app.api.clientId}")
    private String clientId;
    @Value("${app.api.clientSecret}")
    private String clientSecret;

    @Autowired
    GameService gameService;

    @GetMapping("/")
    public String home_() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public void home(Model model) {
        List<GameSchedule> gameSchedules = gameService.getSchedule();
        List<GamePlayer> gamePlayers = gameService.getPlayer();

//        getNews();

        model.addAttribute("mapKey", mapKey);
        model.addAttribute("weatherKey", weatherKey);
        model.addAttribute("schedules", gameSchedules);
        model.addAttribute("players", gamePlayers);
    }

    // 아직 하는중!!!
//    @GetMapping("/api/naver")
//    public String naver(){
//
//        String query = "기아타이거즈";
//        ByteBuffer buffer = StandardCharsets.UTF_8.encode(query);
//        String encode = StandardCharsets.UTF_8.decode(buffer).toString();
//
//        URI uri = UriComponentsBuilder
//                .fromUriString("https://openapi.naver.com")
//                .path("/v1/search/news.xml")
//                .queryParam("query",encode)
//                .queryParam("display",7)
//                .queryParam("start",1)
//                .queryParam("sort","sim")
//                .encode()
//                .build()
//                .toUri();
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        // 아래는 헤더를 넣기 위함
//        RequestEntity<Void> req = RequestEntity
//                .get(uri)
//                .header("X-Naver-Client-Id", clientId)
//                .header("X-Naver-Client-Secret",clientSecret)
//                .build();
//
//        ResponseEntity<String> result = restTemplate.exchange(req, String.class);
//        return result.getBody();
//    }
}
