package ru.netology.service;

import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.Collection;
import java.util.List;


@Service
public class PostService {
  private final PostRepository repository;

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public Collection<Post> all() {
    return repository.all();
  }

  public Post getById(long id) {
    return repository.getById(id).orElseThrow(NotFoundException::new);
  }

  public Post save(Post post) {
    return repository.save(post);
  }

  public void removeById(long id) {
    List<Post> list = repository.all().stream()
            .filter(x -> x.getId() == id)
            .toList();
    if (!list.isEmpty()){
      repository.removeById(id);
    } else {
      throw new NotFoundException();
    }
  }
}