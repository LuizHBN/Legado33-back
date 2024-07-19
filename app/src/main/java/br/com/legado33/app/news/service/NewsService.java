package br.com.legado33.app.news.service;

import br.com.legado33.app.news.News;
import br.com.legado33.app.news.dto.NewNewsDTO;
import br.com.legado33.app.news.dto.ReadNewsDTO;
import br.com.legado33.app.news.dto.UpdateNewsDTO;
import br.com.legado33.app.news.exceptions.NewsNotFoundException;
import br.com.legado33.app.news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
    private final NewsRepository newsRepository;
    @Autowired
    public NewsService(NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }

    public Page<ReadNewsDTO> getAllCategories(Pageable page){
        return newsRepository.findAll(page).map(ReadNewsDTO :: new);
    }

    public ReadNewsDTO saveNewNews(NewNewsDTO newsDTO){
        News news = new News(newsDTO);
        News savedNews = newsRepository.save(news);
        return new ReadNewsDTO(savedNews);
    }

    public ReadNewsDTO findNewsById(Long id){
        return newsRepository.findById(id)
                .map(ReadNewsDTO::new)
                .orElseThrow(() -> new NewsNotFoundException(id));
    }

    public ReadNewsDTO update(UpdateNewsDTO newsDTO, Long id) {
        News existingNews= newsRepository
                .findById(id)
                .orElseThrow(() -> new NewsNotFoundException(id));
        existingNews = updateNewsFromDTO(newsDTO, existingNews);

        return new ReadNewsDTO(newsRepository.save(existingNews));
    }

    public News updateNewsFromDTO(UpdateNewsDTO newsDTO,News news) {
        if(!newsDTO.title().equals(news.getTitle())){
            news.setTitle(newsDTO.title());
        }
        return news;
    }

    public void delete(Long id) {
        newsRepository.findById(id).orElseThrow(() -> new NewsNotFoundException(id));
        newsRepository.deleteById(id);
    }
}
