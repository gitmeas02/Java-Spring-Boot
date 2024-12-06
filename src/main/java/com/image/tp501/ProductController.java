package com.image.tp501;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductController {
    private final String PathUpload = "images/";  // Directory for saving images
    @Autowired
    private Repository repository;

    @GetMapping("/create")
    public String ShowCreatePage(Model model) {
        model.addAttribute("product", new Entities()); 
        model.addAttribute("contentTemplate", "fragments/create");
        model.addAttribute("titleHeader", "Create page with images");
        return "products";
    }

    @PostMapping("/save")
    public String postImage(@RequestParam("name") String name, 
                            @RequestParam("image") MultipartFile[] images, 
                            Model model) {
        StringBuilder imageUrls = new StringBuilder();
        // Process each uploaded image
        for (MultipartFile image : images) {
            if (!image.isEmpty()) {
                try {
                    // Save each image
                    Path imagePath = Paths.get(PathUpload + image.getOriginalFilename());
                    Files.createDirectories(imagePath.getParent());  // Create directories if they don't exist
                    Files.write(imagePath, image.getBytes());  // Write image to disk

                    // Add the image URL to the list
                    if (imageUrls.length() > 0) {
                        imageUrls.append(",");  // Add a separator if there are multiple images
                    }
                    imageUrls.append("/files/" + image.getOriginalFilename());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // Save the product with the images URLs
        repository.save(new Entities(name, imageUrls.toString()));
        model.addAttribute("message", "Product created successfully");
        return "redirect:/lists";  // Redirect to the product list page
    }

    @GetMapping("/files/{file:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable("file") String filename) {
        Path file = Paths.get(PathUpload).resolve(filename);
        try {
            Resource resource = new UrlResource(file.toUri());
            if (resource.isReadable() || resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/lists")
    public String showLists(Model model) {
        List<Entities> products = repository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("contentTemplate", "fragments/list");
        model.addAttribute("titleHeader", "List page with images");
        return "products"; // Main template name
    }
    

    @GetMapping("/home")
    public String ShowHomepage(Model model) {
        model.addAttribute("contentTemplate", "fragments/home");
        model.addAttribute("titleHeader", "Home page with images");
        return "products";
    }
}

