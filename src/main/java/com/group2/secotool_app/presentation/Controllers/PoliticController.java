package com.group2.secotool_app.presentation.Controllers;

import com.group2.secotool_app.bussiness.service.IPoliticService;
import com.group2.secotool_app.model.dto.PoliticDto;
import com.group2.secotool_app.model.dto.request.PoliticRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/politics")
public class PoliticController {

    private final IPoliticService productPoliticService;

    @GetMapping("/open")
    public ResponseEntity<List<PoliticDto>> getallPolitics(){
        return ResponseEntity.ok(productPoliticService.findAll());
    }

    @PostMapping("/admin")
    public ResponseEntity<String> savePolitic(@RequestBody @Valid
                                                  PoliticRequestDto politicRequestDto
    ){
        productPoliticService.save(politicRequestDto);
        return ResponseEntity.ok("politic successfully saved");
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<String> updatePolitic(@PathVariable Long id,
                                                @RequestBody @Valid
                                                PoliticRequestDto politicRequestDto){
        productPoliticService.update(politicRequestDto, id);
        return ResponseEntity.ok("politic successfully updated");
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deletePolitic(@PathVariable Long id){
        productPoliticService.delete(id);
        return ResponseEntity.ok(String.format("politic %s successfully deleted", id));
    }
}
