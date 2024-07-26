package br.com.legado33.app.api.controller;

import br.com.legado33.app.api.controller.dto.request.newDTO.NewNewsDTO;
import br.com.legado33.app.api.controller.dto.response.ReadNewsDTO;
import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateNewsDTO;
import br.com.legado33.app.domain.news.exception.NewsNotFoundException;
import br.com.legado33.app.domain.news.service.NewsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/novidade")
public class NewsController {

    private final NewsService newsService;
    @Autowired
    public NewsController(NewsService newsService){
        this.newsService = newsService;
    }

    @PostMapping("/save")
    public ResponseEntity<ReadNewsDTO> createNews(@RequestBody @Valid NewNewsDTO newsDTO){
        return ResponseEntity.ok(newsService.saveNewNews(newsDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ReadNewsDTO>> getAllCategories(Pageable page){
        return ResponseEntity.ok(newsService.getAllCategories(page));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReadNewsDTO> getNewsById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(newsService.findNewsById(id));
        } catch (NewsNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReadNewsDTO> updateNews(@PathVariable Long id, @Valid @RequestBody UpdateNewsDTO newsDTO) {
        if (newsDTO == null){
            return ResponseEntity.notFound().build();
        }
        try {
            return ResponseEntity.ok(newsService.update(newsDTO, id));
        } catch (NewsNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        try{
            newsService.delete(id);
            return ResponseEntity.noContent().build();
        } catch(NewsNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
