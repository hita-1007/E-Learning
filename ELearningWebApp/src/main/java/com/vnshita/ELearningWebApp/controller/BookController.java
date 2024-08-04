package com.vnshita.ELearningWebApp.controller;

import com.amazonaws.services.s3.model.S3Object;
import com.vnshita.ELearningWebApp.model.Book;
import com.vnshita.ELearningWebApp.service.BookService;
import com.vnshita.ELearningWebApp.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookService bookService;



    private final S3Service s3Service;

    public BookController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
        Book book = bookService.getBookById(bookId);

        // Generate pre-signed URLs for the book's file and image
        String filePresignedUrl = s3Service.generatePresignedUrl(book.getFileKey(), 7).toString(); // 1 year expiration
        String imagePresignedUrl = s3Service.generatePresignedUrl(book.getImageKey(), 7).toString(); // 1 year expiration

        // Set the pre-signed URLs in the book object
        book.setFileKey(filePresignedUrl);
        book.setImageKey(imagePresignedUrl);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    /*@PostMapping("/uploadBook")
    public ResponseEntity<String> uploadBook(
            @RequestParam("title") String title,
            @RequestParam("authorFirstName") String authorFirstName,
            @RequestParam("authorLastName") String authorLastName,
            @RequestParam("categoryName") String categoryName,
            @RequestParam("publicationYear") String publicationYear,
            @RequestParam("userId") Long userId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("image") MultipartFile image) throws IOException {

        try {
            String fileUrl = s3Service.uploadFile(convertMultiPartToFile(file));
            String imageUrl = s3Service.uploadFile(convertMultiPartToFile(image));

            bookService.addBook(title, authorFirstName, authorLastName, categoryName, publicationYear,userId,fileUrl,imageUrl);
            return ResponseEntity.ok("Book uploaded successfully");
            //InputStream inputStream = file.getInputStream();
            //String key = file.getOriginalFilename(); // Use a unique key or filename
            //String imageUrl = s3Service.uploadImageFile(convertMultiPartToFile(image));
            //s3Service.uploadFile(key, inputStream, file.getSize(), file.getContentType());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Book upload failed: " + e.getMessage());
        }
    }*/

    @PostMapping("/uploadBook")
    public ResponseEntity<String> uploadBook(
            @RequestParam("title") String title,
            @RequestParam("authorFirstName") String authorFirstName,
            @RequestParam("authorLastName") String authorLastName,
            @RequestParam("categoryName") String categoryName,
            @RequestParam("publicationYear") String publicationYear,
            @RequestParam("userId") Long userId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("image") MultipartFile image) {

        try {
            // Generate unique keys for files
            String fileKey = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String imageKey = System.currentTimeMillis() + "_" + image.getOriginalFilename();

            // Upload files
            String fileUrl = s3Service.uploadFile(fileKey, file.getInputStream(), file.getSize(), file.getContentType());
            String imageUrl = s3Service.uploadFile(imageKey, image.getInputStream(), image.getSize(), image.getContentType());

            // Save book details
            bookService.addBook(title, authorFirstName, authorLastName, categoryName, publicationYear, userId, fileKey, imageKey);

            return ResponseEntity.ok("Book uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book upload failed: " + e.getMessage());
        }
    }


    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        file.transferTo(convFile);
        return convFile;
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("filename") String filename) {
        try {
            System.out.println("Downloading file: " + filename);
            S3Object s3Object = s3Service.getObject(filename);
            InputStream inputStream = s3Object.getObjectContent();
            byte[] content = inputStream.readAllBytes();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(content);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }



}
