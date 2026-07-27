package com.zomato.service;

import com.zomato.dto.AddressRequest;
import com.zomato.dto.AddressResponse;

import java.util.List;

public interface AddressService {

    AddressResponse addAddress(AddressRequest request);

    List<AddressResponse> getUserAddresses(Long userId);

    AddressResponse getAddress(Long id);

    AddressResponse updateAddress(Long id, AddressRequest request);

    void deleteAddress(Long id);

    AddressResponse setDefaultAddress(Long id);

}