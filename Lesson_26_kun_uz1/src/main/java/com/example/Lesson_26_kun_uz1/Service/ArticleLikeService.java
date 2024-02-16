package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.Entity.ArticleEntity;
import com.example.Lesson_26_kun_uz1.Entity.ArticleLikeEntity;
import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import com.example.Lesson_26_kun_uz1.Enums.LikeStatus;
import com.example.Lesson_26_kun_uz1.Exp.AppBadException;
import com.example.Lesson_26_kun_uz1.Repository.ArticleRepository;
import com.example.Lesson_26_kun_uz1.Repository.ArticleLikeRepository;
import com.example.Lesson_26_kun_uz1.Repository.Profilerepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class ArticleLikeService {
    @Autowired
   public ArticleLikeRepository likeRepository;

    @Autowired
    public ArticleRepository articleRepository;

    @Autowired
    public Profilerepository profilerepository;

    public boolean addLike(String articleID,Integer profileID) {

        Optional<ArticleEntity> articleEntity1 = articleRepository.findById(articleID);
        if (articleEntity1.isEmpty()) {
            log.warn("like uchun bunday articleID yo`q ");
            throw new AppBadException("like uchun bunday articleID yo`q");
        }
        Optional<ProfileEntity> profileEntity1 = profilerepository.findById(profileID);
        if (profileEntity1.isEmpty()) {
            log.warn("like uchun bunday profileID yo`q ");
            throw new AppBadException("like uchun bunday profileID yo`q");
        }

        Optional<ArticleLikeEntity> articleLikeEntityFind = likeRepository.find(articleID, profileID);

        //hali umuman Like yoki disLike bosilmagan bo`lsa kiradi
        if (articleLikeEntityFind.isEmpty()){
            ArticleLikeEntity articleLikeEntity=new ArticleLikeEntity();
            articleLikeEntity.setStatus(LikeStatus.LIKE);
            articleLikeEntity.setProfileId(profileID.longValue());
            articleLikeEntity.setArticleId(articleID);
            articleLikeEntity.setCreateDate(LocalDateTime.now());
            likeRepository.save(articleLikeEntity);
        }else {
            //Agar bosilgan bo`lsa kiradi va holatga qarab o`zgartiriladi.
            ArticleLikeEntity articleLikeEntity1 = articleLikeEntityFind.get();
          articleLikeEntity1.setStatus(LikeStatus.LIKE);
            likeRepository.save(articleLikeEntity1);
        }


        return true;

    }

    public Object addDisLike(String articleID,Integer profileId) {
        Optional<ArticleEntity> articleEntity1 = articleRepository.findById(articleID);
        if (articleEntity1.isEmpty()) {
            log.warn("like uchun bunday articleID yo`q ");
            throw new AppBadException("like uchun bunday articleID yo`q");
        }
        Optional<ProfileEntity> profileEntity1 = profilerepository.findById(profileId);
        if (profileEntity1.isEmpty()) {
            log.warn("like uchun bunday profileID yo`q ");
            throw new AppBadException("like uchun bunday profileID yo`q");
        }
        Optional<ArticleLikeEntity> articleLikeEntityFind = likeRepository.find(articleID, profileId);
        //hali umuman Like yoki disLike bosilmagan bo`lsa kiradi
        if (articleLikeEntityFind.isEmpty()){
            ArticleLikeEntity articleLikeEntity=new ArticleLikeEntity();
            articleLikeEntity.setStatus(LikeStatus.DISLIKE);
            articleLikeEntity.setProfileId(profileId.longValue());
            articleLikeEntity.setArticleId(articleID);
            articleLikeEntity.setCreateDate(LocalDateTime.now());
            likeRepository.save(articleLikeEntity);
        }else {
            // Agar bosilgan bo`lsa kiradi va holatga qarab o`zgartiriladi.
            ArticleLikeEntity articleLikeEntity1 = articleLikeEntityFind.get();
            articleLikeEntity1.setStatus(LikeStatus.DISLIKE);
            likeRepository.save(articleLikeEntity1);
        }
        return true;
    }

    public boolean remove(String articleID, Integer id) {
        Optional<ArticleLikeEntity> articleLikeEntity = likeRepository.find(articleID, id);
        if (articleLikeEntity.isEmpty()){
            throw new AppBadException("o`zi yo`q o`chirilmadi");
        }
        ArticleLikeEntity articleLikeEntity1 = articleLikeEntity.get();
        articleLikeEntity1.setStatus(null);
        likeRepository.save(articleLikeEntity1);
        return true;
    }
}
