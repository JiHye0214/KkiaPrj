package com.project.kkiaprj.service;

import com.project.kkiaprj.Util.U;
import com.project.kkiaprj.domain.Post;
import com.project.kkiaprj.domain.PostComment;
import com.project.kkiaprj.domain.PostImg;
import com.project.kkiaprj.repository.PostCommentRepository;
import com.project.kkiaprj.repository.PostImgRepository;
import com.project.kkiaprj.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

    @Value("${app.upload.path}")
    private String uploadDir;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostImgRepository postImgRepository;
    @Autowired
    private PostCommentRepository postCommentRepository;

    @Override
    public List<Post> getPostList(Integer page, String sq, Model model) {
        if (page < 1) page = 1;

        int pagesPerSection = 5;
        int rowsPerPage = 10;

        Page<Post> pagedPost = null;

        if (sq.isEmpty()) {
            pagedPost = postRepository.findAll(PageRequest.of(page - 1, rowsPerPage, Sort.by(Sort.Order.desc("id"))));;
        } else {
            pagedPost = postRepository.findByTitleContains(sq, PageRequest.of(page - 1, rowsPerPage, Sort.by(Sort.Order.desc("id"))));
        }

        long totalLength = pagedPost.getTotalElements();
        int totalPage = pagedPost.getTotalPages();

        int startPage = 0;
        int endPage = 0;

        List<Post> lists = new ArrayList<>();

        if (totalLength > 0) {
            if (page > totalPage) {
                page = totalPage;

                if (sq.isEmpty()) {
                    pagedPost = postRepository.findAll(PageRequest.of(page - 1, rowsPerPage, Sort.by(Sort.Order.desc("id"))));
                } else {
                    pagedPost = postRepository.findByTitleContains(sq, PageRequest.of(page - 1, rowsPerPage, Sort.by(Sort.Order.desc("id"))));
                }
            }

            startPage = (((page - 1) / pagesPerSection) * pagesPerSection) + 1;
            endPage = startPage + pagesPerSection - 1;
            if (endPage > totalPage) endPage = totalPage;

            lists = pagedPost.getContent();

            model.addAttribute("lists", lists);
        } else {
            page = 0;
        }

        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("sq", sq);

        model.addAttribute("url", U.getRequest().getRequestURI()); // /community/post/list
        model.addAttribute("rowsPerPage", rowsPerPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return lists;
    }

    @Override
    public Post getPost(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if(post != null) {
            post.setViewCnt(post.getViewCnt() + 1);
            postRepository.saveAndFlush(post);
        }
        return post;
    }

    @Override
    @Transactional
    public int writePost(Post post, Map<String, MultipartFile> file) {

        post.setUser(U.getLoggedUser());
        postRepository.saveAndFlush(post);

        if(file != null) {
            resistImg(file, post);
        }

        return 1;
    }

    @Override
    public int updatePost(Post post, Map<String, MultipartFile> file, Long[] delFile) {

        resistImg(file, post);

        if(delFile != null){
            for(Long fileId : delFile) {
                PostImg img = postImgRepository.findById(fileId).orElse(null);
                if(img != null) {
                    img.setPostId(post.getId());
                    deleteImgs(img); // upload 삭제
                    postImgRepository.delete(img); // db 삭제
                }
            }
        }

        Post update = postRepository.findById(post.getId()).orElse(null);
        List<PostImg> postImg = postImgRepository.findByPostId(post.getId());

        assert update != null;
        update.setTitle(post.getTitle());
        update.setContent(post.getContent());
        update.setCategory(post.getCategory());
        update.setUser(U.getLoggedUser());
        update.setPostImgs(postImg);
        postRepository.saveAndFlush(update);
        return 1;
    }

    private void deleteImgs(PostImg img) {
        String saveDirectory = new File(uploadDir).getAbsolutePath();

        File f = new File(saveDirectory, img.getFileName()); // 원본이 삭제 대상
        if(f.exists()) {
            f.delete();
        }
    }

    public void resistImg(Map<String, MultipartFile> file, Post post){

        // 일단 파일 하나씩 보기
        for(Map.Entry<String, MultipartFile> e : file.entrySet()){

            // 하나씩) 이름 확인
            if(!e.getKey().startsWith("upload")) continue;

            // 하나씩) 물리적 파일 저장
            PostImg postImg = upload(e.getValue()); // 함수가 UserImg 타입을 반환

            // 하나씩) DB 저장
            if(postImg != null) {
                postImg.setPostId(post.getId());
                postImgRepository.saveAndFlush(postImg);
            }
        }
    }

    public PostImg upload(MultipartFile multipartFile) {

        PostImg postImg = null;

        String sourceName = null;
        String fileName = null;

        String originalFilename = multipartFile.getOriginalFilename();
        if(originalFilename == null || originalFilename.isEmpty()) return null;

        sourceName = StringUtils.cleanPath(originalFilename);
        fileName = sourceName;

        File file = new File(uploadDir, fileName);

        if(file.exists()){  // 이미 존재하는 파일명,  중복된다면 다른 이름은 변경하여 파일 저장
            // a.txt => a_2378142783946.txt  : time stamp 값을 활용할거다!
            // "a" => "a_2378142783946" : 확장자 없는 경우

            int pos = fileName.lastIndexOf(".");
            if(pos > -1){  // 확장자 있는 경우
                String name = fileName.substring(0, pos);   // 파일 '이름'
                String ext = fileName.substring(pos + 1);  // 파일 '확장자'

                fileName = name + "_" + System.currentTimeMillis() + "." + ext;
            } else {  // 확장자 없는 경우
                fileName += "_" + System.currentTimeMillis();
            }
        }

        Path copyOfLocation = Paths.get(new File(uploadDir, fileName).getAbsolutePath());

        try {
            Files.copy(
                    multipartFile.getInputStream(),
                    copyOfLocation,
                    StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        postImg = PostImg.builder()
                .fileName(fileName)
                .sourceName(sourceName)
                .build();

        return postImg;
    }

    @Override
    public int deletePost(Post post) {
        Post delete = postRepository.findById(post.getId()).orElse(null);
        assert delete != null;
        postRepository.delete(delete);
        return 1;
    }

    // 댓글 작성
    @Override
    public int writePostComment(Long listItemId, PostComment postComment) {
        postComment.setPostId(listItemId);
        postComment.setUser(U.getLoggedUser());
        postCommentRepository.saveAndFlush(postComment);
        return 1;
    }

    // 댓글 삭제
    @Override
    public int deletePostComment(PostComment postComment) {
        postCommentRepository.delete(postComment);
        return 1;
    }
}
