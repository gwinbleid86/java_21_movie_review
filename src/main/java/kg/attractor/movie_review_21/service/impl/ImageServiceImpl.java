package kg.attractor.movie_review_21.service.impl;

import kg.attractor.movie_review_21.dao.ImageDao;
import kg.attractor.movie_review_21.dto.ImageRequestDto;
import kg.attractor.movie_review_21.errors.CanNotFindImageException;
import kg.attractor.movie_review_21.model.Image;
import kg.attractor.movie_review_21.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageDao imageDao;

    @Override
    public void upload(ImageRequestDto request) {
        Image image = new Image();
        image.setFilename(ImageService.uploadImage(request.getFile()));
        image.setMovieId(request.getMovieId());

        imageDao.save(image);
        log.info("Image uploaded successfully");
    }

    @Override
    public ResponseEntity<?> download(Long id) {
        Image image = imageDao.getImage(id)
                .orElseThrow(() -> new CanNotFindImageException("Can not find image with Id: " + id));
        var response = ImageService.downloadImage(image.getFilename(), MediaType.IMAGE_JPEG);
        log.info("Image downloaded successfully");
        return response;
    }


}
