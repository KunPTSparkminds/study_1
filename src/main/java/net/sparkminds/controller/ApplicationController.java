package net.sparkminds.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import lombok.RequiredArgsConstructor;
import net.sparkminds.dto.ApplicationRequestDto;
import net.sparkminds.dto.ApplicationResponseDto;
import net.sparkminds.exception.EntityNotFoundException;
import net.sparkminds.service.ApplicationService;
import net.sparkminds.utils.PDFGenerator;

@RestController
@RequestMapping("api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping
    public ResponseEntity<List<ApplicationResponseDto>> getApplication() {
        List<ApplicationResponseDto> applications = applicationService.getAllApplication();
        return ResponseEntity.ok().body(applications);
    };

    @GetMapping("/{id}")
    public ResponseEntity<?> getApplicationById(@PathVariable("id") Long id) {
        ApplicationResponseDto application = applicationService.getApplicationById(id)
                .orElseThrow(() -> new EntityNotFoundException("Application is not exist"));
        return ResponseEntity.ok().body(application);
    };

    @PostMapping("/add-new")
    public ResponseEntity<?> postNewApplication(@RequestBody @Valid ApplicationRequestDto applicationRequestDto) {

        ApplicationResponseDto newApplication = applicationService.createApplication(applicationRequestDto);

        if (newApplication == null) {
            return ResponseEntity.badRequest().body("Application is already exists");
        }

        return ResponseEntity.ok().body(newApplication);
    };

    @PostMapping("/add-current")
    public ResponseEntity<?> postCurrentApplication(@RequestBody @Valid ApplicationRequestDto applicationRequestDto) {
        applicationService.deleteApplicationWithEmail(applicationRequestDto.getEmail());
        ApplicationResponseDto newApplication = applicationService.createApplication(applicationRequestDto);
        return ResponseEntity.ok().body(newApplication);
    };

//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateApplication(@PathVariable("id") Long id,
//            @RequestBody ApplicationRequestDto applicationRequestDto) {
//        applicationService.updateApplication(applicationRequestDto, id);
//        return ResponseEntity.noContent().build();
//    };

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteApplication(@PathVariable("id") Long id) {
        applicationService.deleteApplicationWithId(id);
        return ResponseEntity.noContent().build();
    };

    @GetMapping("/pdf")
    public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);

        List<ApplicationResponseDto> applications = applicationService.getAllApplication();

        PDFGenerator generator = new PDFGenerator();
        generator.setApplications(applications);
        generator.generate(response);

    }

    @GetMapping("/pdf/{id}")
    public void generatePdfById(HttpServletResponse response, @PathVariable("id") Long id)
            throws DocumentException, IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);

        ApplicationResponseDto application = applicationService.getApplicationById(id).get();

        PDFGenerator generator = new PDFGenerator();
        generator.setApplication(application);
        generator.generate(response);

    }

}
