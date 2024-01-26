package com.project.kkiaprj.controller;

import com.project.kkiaprj.domain.Market;
import com.project.kkiaprj.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private MarketService marketService;

    // 마켓 리스트
    @GetMapping("/list")
    public void marketList(Model model){
        marketService.getMarketList(model);
    };

    // 마켓 상세
    @GetMapping("/detail/{id}")
    public String marketDetail(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("market", marketService.getMarket(id));
        return "market/detail";
    }

    // 마켓 작성
    @GetMapping("/write")
    public void marketWrite(){};

    // 마켓 수정
    @GetMapping("/update/{id}")
    public String marketUpdate(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("market", marketService.getMarket(id));
        return "market/update";
    }

    @PostMapping("/write")
    public String marketWritePost(Market market,
                                  RedirectAttributes redirectAttrs,
                                  Model model) {

        System.out.println("----------------------------------------");
        System.out.println(market);

        model.addAttribute("result", marketService.writeMarket(market));
        model.addAttribute("action", "작성");
        return "market/success";
    }
}
