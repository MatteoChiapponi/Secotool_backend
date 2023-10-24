package com.group2.secotool_app.bussiness.service;

import com.group2.secotool_app.model.entity.Product;
import com.group2.secotool_app.model.entity.Rent;
import com.group2.secotool_app.model.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface IRentService {
    void saveRent(Product prodToRent, LocalDate startDate, LocalDate endDate, User user, Long totalDays, Double totalPrice);

    List<Rent> getUserHistoryOfRentals(Long userId);
}
