package com.group2.secotool_app.bussiness.service.Impl;

import com.group2.secotool_app.bussiness.service.IRentService;
import com.group2.secotool_app.bussiness.service.IUserService;
import com.group2.secotool_app.model.entity.Product;
import com.group2.secotool_app.model.entity.Rent;
import com.group2.secotool_app.model.entity.User;
import com.group2.secotool_app.persistence.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements IRentService {

    private final RentRepository rentRepository;
    private final IUserService userService;

    @Override
    public void saveRent(Product prodToRent, LocalDate startDate, LocalDate endDate, User user, Long totalDays, Double totalPrice) {
        Rent rent = new Rent();
        rent.setRentalStartDate(startDate);
        rent.setRentalEndDate(endDate);
        rent.setTotalDays(totalDays);
        rent.setRentalPrice(totalPrice);
        rent.setUser(user);
        rent.setProduct(prodToRent);
        rentRepository.save(rent);
    }

    @Override
    public List<Rent> getUserHistoryOfRentals(Long userId) {
        var user = userService.findUserById(userId);
        var userRentals = user.getRents();
        return userRentals;
    }
}
