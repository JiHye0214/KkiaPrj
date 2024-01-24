package com.project.kkiaprj;

import com.project.kkiaprj.domain.Food;
import com.project.kkiaprj.domain.FoodComment;
import com.project.kkiaprj.domain.FoodItem;
import com.project.kkiaprj.domain.User;
import com.project.kkiaprj.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class setData {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private FoodCommentRepository foodCommentRepository;

    @Autowired
    private FoodSaveRepository foodSaveRepository;

    // 디비버에서 하는 게 더 빠름
//    @Test void dropTables() {
//        foodSaveRepository.dropTable();
//        foodCommentRepository.dropTable();
//        foodItemRepository.dropTable();
//        foodRepository.dropTable();
//        userRepository.dropTable();
//    }

    // User 데이터 생성
    @Test
    public void insertUser() {
        userRepository.deleteAllInBatch();
        userRepository.setIdAsOne();

        List<String> loginId = List.of("1234", "apple1234", "melon1234", "cherry1234");
        List<String> password = List.of("1234", "1234", "1234", "1234");
        List<String> userName = List.of("김우승", "박사과", "김멜론", "이체리");
        List<String> nickName = List.of("어우기", "말랑한고구마", "맑은아침햇살", "마라탕먹고싶다");
        List<String> email = List.of("djdnrl@mail.com", "apple@mail.com", "melon@mail.com", "cherry@mail.com");

        List<User> users = new ArrayList<>();

        for (int i = 0; i < loginId.size(); i++) {
            User user = User.builder()
                    .loginId(loginId.get(i))
                    .password(password.get(i))
                    .name(userName.get(i))
                    .nickname(nickName.get(i))
                    .email(email.get(i))
                    .build();

            users.add(user);
        }

        userRepository.saveAll(users);
    }

    @Test
    public void insertData() {
        System.out.println("─".repeat(60) + "\n");

        // --------------------------------------------------------------------------------

        // 기존 데이터 삭제
        foodSaveRepository.deleteAllInBatch();
        foodCommentRepository.deleteAllInBatch();
        foodItemRepository.deleteAllInBatch();
        foodRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();

        // 테이블 id autoIncrement 1 로 설정
        foodSaveRepository.setIdAsOne();
        foodCommentRepository.setIdAsOne();
        foodItemRepository.setIdAsOne();
        foodRepository.setIdAsOne();
        userRepository.setIdAsOne();

        // --------------------------------------------------------------------------------

        // User 데이터 생성
        List<String> loginId = List.of("1234", "apple1234", "melon1234", "cherry1234");
        List<String> password = List.of("$2a$10$K6ipjV2LUKV2ncw3FE9wwe1PEn3lHepog5kKu/vutJ2K9HFLQ/12m",
                                        "$2a$10$6gVaMy7.lbezp8bGRlV2fOArmA3WAk2EHxSKxncnzs28/m3DXPyA2",
                                        "$2a$10$7LTnvLaczZbEL0gabgqgfezQPr.xOtTab2NAF/Yt4FrvTSi0Y29Xa",
                                        "$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi");
        List<String> userName = List.of("김우승", "박사과", "김멜론", "이체리");
        List<String> nickName = List.of("어우기", "말랑한고구마", "맑은아침햇살", "마라탕먹고싶다");
        List<String> email = List.of("djdnrl@mail.com", "apple@mail.com", "melon@mail.com", "cherry@mail.com");

        List<User> users = new ArrayList<>();

        for (int i = 0; i < loginId.size(); i++) {
            User user = User.builder()
                    .loginId(loginId.get(i))
                    .password(password.get(i))
                    .name(userName.get(i))
                    .nickname(nickName.get(i))
                    .email(email.get(i))
                    .build();

            users.add(user);
        }

        userRepository.saveAll(users);
//        userRepository.findAll().forEach(System.out::println);

        // --------------------------------------------------------------------------------

        // Food 데이터 생성
        List<String> title = List.of("맛집1", "맛집2", "맛집3", "맛집4");
        List<String> region = List.of("광주", "광주", "대구", "부산");
        List<User> user = List.of(users.get(1), users.get(1), users.get(2), users.get(3));

        List<Food> foods = new ArrayList<>();

        for (int i = 0; i < title.size(); i++) {
            Food food = Food.builder()
                    .title(title.get(i))
                    .region(region.get(i))
                    .user(user.get(i))
                    .build();

            foods.add(food);
        }

        foodRepository.saveAll(foods);
//        foodRepository.findAll().forEach(System.out::println);

        // --------------------------------------------------------------------------------

        // FoodItem 데이터 생성
        List<String> restaurantName = List.of("라스트춘선 안양점", "호랑이굴", "호랑이굴", "호랑이굴", "호랑이굴");
        String content = "로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다. 로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.";
        List<String> address = List.of("경기 안양시 동안구 호계동 1045-9", "경기 안양시 동안구 호계동 1043-1", "경기 안양시 동안구 호계동 1043-1", "경기 안양시 동안구 호계동 1043-1", "경기 안양시 동안구 호계동 1043-1");
        List<Double> lat = List.of(37.3908408022861, 37.39201269687231, 37.39201269687231, 37.39201269687231, 37.39201269687231);
        List<Double> lng = List.of(126.953373087799, 126.95482912267362, 126.95482912267362, 126.95482912267362, 126.95482912267362);
        List<Long> foodId = List.of(foods.get(0).getId(), foods.get(0).getId(), foods.get(1).getId(), foods.get(2).getId(), foods.get(3).getId());

        List<FoodItem> foodItems = new ArrayList<>();

        for (int i = 0; i < restaurantName.size(); i++) {
            FoodItem foodItem = FoodItem.builder()
                    .restaurantName(restaurantName.get(i))
                    .content(content)
                    .address(address.get(i))
                    .lat(lat.get(i))
                    .lng(lng.get(i))
                    .food(foodId.get(i))
                    .build();

            foodItems.add(foodItem);
        }

        foodItemRepository.saveAll(foodItems);
//        foodItemRepository.findAll().forEach((System.out::println));

        // --------------------------------------------------------------------------------

        // FoodComment 데이터 생성
        List<String> cmtContent = List.of(
                "말랑한고구마가 1번 글에 댓글 작성", "말랑한고구마가 2번 글에 댓글 작성", "말랑한고구마가 3번 글에 댓글 작성", "말랑한고구마가 4번 글에 댓글 작성",
                "맑은아침햇살이 1번 글에 댓글 작성", "맑은아침햇살이 2번 글에 댓글 작성", "맑은아침햇살이 3번 글에 댓글 작성", "맑은아침햇살이 4번 글에 댓글 작성",
                "마라탕먹고싶다가 1번 글에 댓글 작성", "마라탕먹고싶다가 2번 글에 댓글 작성", "마라탕먹고싶다가 3번 글에 댓글 작성", "마라탕먹고싶다가 4번 글에 댓글 작성"
        );
        List<User> cmtUser = List.of(
                users.get(1), users.get(1), users.get(1), users.get(1),
                users.get(2), users.get(2), users.get(2), users.get(2),
                users.get(3), users.get(3), users.get(3), users.get(3)
        );
        List<Long> cmtFoodId = List.of(
                foods.get(0).getId(), foods.get(1).getId(), foods.get(2).getId(), foods.get(3).getId(),
                foods.get(0).getId(), foods.get(1).getId(), foods.get(2).getId(), foods.get(3).getId(),
                foods.get(0).getId(), foods.get(1).getId(), foods.get(2).getId(), foods.get(3).getId()
        );

        List<FoodComment> foodComments = new ArrayList<>();

        for (int i = 0; i < cmtContent.size(); i++) {
            FoodComment foodComment = FoodComment.builder()
                    .content(cmtContent.get(i))
                    .user(cmtUser.get(i))
                    .food(cmtFoodId.get(i))
                    .build();

            foodComments.add(foodComment);
        }

        foodCommentRepository.saveAll(foodComments);
//        foodCommentRepository.findAll().forEach((System.out::println));

        // --------------------------------------------------------------------------------

        // FoodSave 데이터 생성
//        List<Long> saveUserId = List.of();
//        List<Long> saveFoodId = List.of();
//
//        List<FoodSave> foodSaves = new ArrayList<>();
//
//        for (int i = 0; i < saveUserId.size(); i++) {
//            FoodSave foodSave = FoodSave.builder()
//                .user(saveUserId.get(i))
//                .food(saveFoodId.get(i))
//                .build();
//
//            foodSaves.add(foodSave);
//        }

        // --------------------------------------------------------------------------------

        System.out.println("─".repeat(60) + "\n");
    }

}
