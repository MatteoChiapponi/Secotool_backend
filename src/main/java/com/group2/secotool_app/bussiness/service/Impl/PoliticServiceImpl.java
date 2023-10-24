package com.group2.secotool_app.bussiness.service.Impl;

import com.group2.secotool_app.bussiness.mapper.PoliticDtoMapper;
import com.group2.secotool_app.bussiness.mapper.PoliticMapper;
import com.group2.secotool_app.bussiness.service.IPoliticService;
import com.group2.secotool_app.model.dto.PoliticDto;
import com.group2.secotool_app.model.dto.request.PoliticRequestDto;
import com.group2.secotool_app.persistence.PoliticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PoliticServiceImpl implements IPoliticService {

    private final PoliticRepository politicRepository;
    private final PoliticMapper politicMapper;
    private final PoliticDtoMapper politicDtoMapper;

    @Override
    public void save(PoliticRequestDto politicRequestDto) {
        if (politicRepository.existsByTitle(politicRequestDto.title()))
            throw new RuntimeException("politic already exists");
        politicRepository.save(politicMapper.toPolitic(politicRequestDto));
    }

    @Override
    public void update(PoliticRequestDto politicRequestDto, Long id) {
        if (!politicRepository.existsById(id))
            throw new RuntimeException("can not update a politic that doesn't exists");
        var politic = politicMapper.toPolitic(politicRequestDto);
        politic.setId(id);
        politicRepository.save(politic);
    }

    @Override
    public void delete(Long id) {
        politicRepository.deleteById(id);
    }

    @Override
    public List<PoliticDto> findAll() {
        var politics = politicRepository.findAll();
        List<PoliticDto> politicsDto = new ArrayList<>();
        politics.forEach(politic -> politicsDto.add(politicDtoMapper.toPoliticDto(politic)));
        return politicsDto;
    }
}
