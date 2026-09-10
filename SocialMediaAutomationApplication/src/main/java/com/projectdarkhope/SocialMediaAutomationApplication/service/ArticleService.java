package com.projectdarkhope.SocialMediaAutomationApplication.service;

import java.util.List;

import com.projectdarkhope.SocialMediaAutomationApplication.ai.GeminiClassification;
import com.projectdarkhope.SocialMediaAutomationApplication.ai.GeminiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdarkhope.SocialMediaAutomationApplication.entity.Article;
import com.projectdarkhope.SocialMediaAutomationApplication.repository.ArticleRepository;

@Service
public class ArticleService 
{
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private GeminiService geminiService;
	
	public Article saveArticle(Article article)
	{
		if(articleRepository.existsByUrl(article.getUrl()))
		{
			throw new RuntimeException("Article with this URL already exists");
		}
		return articleRepository.save(article);
	}
	
	public List<Article> getAllArticles()
	{
		return articleRepository.findAll();
	}
	
	public void processUnprocessedArticles()
	{
	    List<Article> articles = articleRepository.findByProcessedFalse();

	    int count = 0;

	    for(Article article : articles)
	    {
	        if(count >= 5)
	        {
	            break;
	        }

	        try
	        {
	            GeminiClassification classification =
	                    geminiService.classifyArticle(
	                            article.getTitle(),
	                            article.getDescription()
	                    );

	            article.setCategory(classification.getCategory());
	            article.setSubcategory(classification.getSubcategory());
	            article.setTargetAccount(classification.getTargetAccount());
	            article.setRelevance(classification.getRelevance());
	            article.setConfidence(classification.getConfidence());

	            article.setProcessed(true);

	            articleRepository.save(article);

	            count++;

	        }
	        catch(Exception e)
	        {
	            System.out.println("Failed to process article: " + article.getTitle());
	            System.out.println(e.getMessage());
	        }
	    }
	}
}