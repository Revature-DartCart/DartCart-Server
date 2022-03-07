package com.revature.services;

import com.revature.models.Seller;
import com.revature.repositories.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepo sellerRepo;

    @Override
    public Seller addSeller(Seller seller) {
        return sellerRepo.save(seller);
    }

    @Override
    public Optional<Seller> getSellerById(int id) {
        return sellerRepo.findById(id);
    }

    @Override
    public List<Seller> getAllSellers() {
        return (List<Seller>) sellerRepo.findAll();
    }

    @Override
    public void updateSeller(Seller change) {
        sellerRepo.save(change);
    }

    @Override
    public boolean deleteSeller(int id) {
        try {
            sellerRepo.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<Seller> getSellerByUserId(int id) {
        return sellerRepo.findByUserId(id);
    }
}
