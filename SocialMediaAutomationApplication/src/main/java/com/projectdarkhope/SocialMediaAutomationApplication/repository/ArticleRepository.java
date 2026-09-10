package com.projectdarkhope.SocialMediaAutomationApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdarkhope.SocialMediaAutomationApplication.entity.Article;


public interface ArticleRepository extends JpaRepository<Article,Long>
{
	boolean existsByUrl(String url);
	
	List<Article> findByProcessedFalse();
}