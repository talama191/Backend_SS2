package com.example.ecommercebackend.Entities.CartLine;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartLineService {
    @Autowired
    CartLineRepository cartLineRepository;

    public List<CartLine> getAllCartLines(){
        return cartLineRepository.findAll();
    }
    public List<CartLine> getCartLinesByCartID(int cart_id){
        return cartLineRepository.getCartLinesByCart_id(cart_id);
    }
    @Transactional
    public boolean deleteCartLinesByCartID(int cart_id){
        try{

            cartLineRepository.deleteCartLinesByCartID(cart_id);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return  true;
    }

}
