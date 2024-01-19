package com.project.kkiaprj.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.kkiaprj.domain.NewsItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
    public class NewsServiceImpl implements NewsService {

    @Value("${app.api.client-id}")
    private String clientID;

    @Value("${app.api.client-secret}")
    private String clientPW;

    @Override
    public List<NewsItem> getNews() {
        String clientId = clientID;
        String clientSecret = clientPW;

        String text = null;
        try {
            text = URLEncoder.encode("기아 타이거즈", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패", e);
        }

        String apiURL = "https://openapi.naver.com/v1/search/news?query=" + text + "&start=1&display=20&sort=date";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL, requestHeaders);

        return mapResponseToNewsItems(responseBody);
    }

        private List<NewsItem> mapResponseToNewsItems(String responseBody) {
            List<NewsItem> newsItemList = new ArrayList<>();

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(responseBody);

                // JSON에서 필요한 정보 추출 및 NewsItem 객체 생성
                for (JsonNode itemNode : rootNode.path("items")) {
                    NewsItem newsItem = new NewsItem();
                    newsItem.setTitle(itemNode.path("title").asText());
                    newsItem.setLink(itemNode.path("link").asText());
                    newsItem.setDescription(itemNode.path("description").asText());
                    newsItem.setPostDate(itemNode.path("pubDate").asText()); // "pubDate"는 네이버 뉴스 API에서 사용하는 필드명
                    newsItem.setThumbnail(itemNode.path("thumbnail").asText());

                    newsItemList.add(newsItem);
                }
            } catch (IOException e) {
                throw new RuntimeException("API 응답을 매핑하는 데 실패했습니다.", e);
            }

            return newsItemList;
        }
    private static String get(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }
    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
}
