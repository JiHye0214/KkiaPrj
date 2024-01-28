package com.project.kkiaprj.controller;

import com.project.kkiaprj.Util.U;
import com.project.kkiaprj.domain.Market;
import com.project.kkiaprj.service.MarketService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private MarketService marketService;

    // 마켓 리스트
    @GetMapping("/list")
    public String marketList(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                             @RequestParam(name = "sq", required = false, defaultValue = "") String sq,
                             HttpServletRequest request,
                             Model model
    ){
        String uri = U.getRequest().getRequestURI();
        request.getSession().setAttribute("prevPage", uri);

        marketService.getMarketList(page, sq, model);
        return "market/list";
    }

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

    // 마켓 검색 post
    @PostMapping("/search")
    public String marketSearch(String sq,
                               RedirectAttributes redirectAttrs) {
        redirectAttrs.addAttribute("sq", sq);
        return "redirect:/market/list";
    }

    // 마켓 작성 post
    @PostMapping("/write")
    public String marketWritePost(Market market,
                                  @RequestParam Map<String, MultipartFile> file,
                                  Model model) {

        model.addAttribute("result", marketService.writeMarket(market, file));
        model.addAttribute("action", "작성");
        return "market/success";
    }

    // 마켓 삭제 post
    @PostMapping("/delete")
    public String marketDeletePost(Market market, Model model) {

        model.addAttribute("result", marketService.deleteMarket(market));
        model.addAttribute("action", "삭제");
        return "market/success";
    }

    // 마켓 수정 post
    @PostMapping("/update")
    public String marketModify(Market market,
                               Long[] delfile, // 삭제할 파일들
                               @RequestParam Map<String, MultipartFile> file,
                               Model model) {

        model.addAttribute("result", marketService.modifyMarket(market, file, delfile));
        model.addAttribute("action", "수정");
        return "market/success";
    }

}
