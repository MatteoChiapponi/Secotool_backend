package com.group2.secotool_app.bussiness.service;

import com.group2.secotool_app.model.dto.PoliticDto;
import com.group2.secotool_app.model.dto.request.PoliticRequestDto;

import java.util.List;

public interface IPoliticService {
    void save(PoliticRequestDto politicRequestDto);

    void update(PoliticRequestDto politicRequestDto, Long id);

    void delete(Long id);

    List<PoliticDto> findAll();

}
