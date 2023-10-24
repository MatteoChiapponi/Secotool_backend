package com.group2.secotool_app.util;

import com.group2.secotool_app.bussiness.mapper.RentUserDtoMapper;
import com.group2.secotool_app.model.dto.RentUserDto;
import com.group2.secotool_app.model.entity.Rent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RentUtils {

    private final RentUserDtoMapper rentUserDtoMapper;
    public Double calculateTotalPriceOfRent(long totalDays, Double productPrice){
        return productPrice * totalDays;
    }
    public List<RentUserDto> rentalsToUserRentalsDto(List<Rent> rentals){
        List<RentUserDto> rentalsUserDto = new ArrayList<>();
        rentals.forEach(rental -> rentalsUserDto.add(rentUserDtoMapper.toUserRentDto(rental)));
        return rentalsUserDto;
    }
    public List<RentUserDto> sortRentalsByStartDate(List<RentUserDto> rentalsDto){
        rentalsDto.sort((r1,r2) -> r1.compareTo(r2));
        return rentalsDto;
    }

}
