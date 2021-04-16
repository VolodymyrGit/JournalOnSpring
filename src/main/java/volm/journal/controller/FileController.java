//package volm.journal.controller;
//
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@Controller
//public class FileController {
//
//    private static List<String> files = new ArrayList<>();
//    private static final String pathname = "C:\\Users\\Laptop\\Pictures\\Saved Pictures";
//
//
//    @GetMapping("/file")
//    public String getFileView(Model model) {
//
//        return "file";
//    }
//
//
//    @PostMapping("/file")
//    public String post(@RequestParam("file") MultipartFile file, Model model) throws IOException {
//
//        String fileName = uploadFile(file);
//
//        files.add(fileName);
//
//        model.addAttribute("files", files);
//        model.addAttribute("pathname", pathname);
//
//        return "file";
//    }
//
//    private String uploadFile(MultipartFile file) throws IOException {
//
//        File uploadDir = new File(pathname);
//
//        if(!uploadDir.exists()) {
//            uploadDir.mkdir();
//        }
//
//        String uniqueId = UUID.randomUUID().toString();
//        String fileName = uniqueId + "." + file.getOriginalFilename();
//        file.transferTo(new File(uploadDir + File.separator + fileName));
//        return fileName;
//    }
//}
