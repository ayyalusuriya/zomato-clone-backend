package com.zomato.controller;

import com.zomato.dto.AddressRequest;
import com.zomato.dto.AddressResponse;
import com.zomato.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public AddressResponse addAddress(@Valid @RequestBody AddressRequest request) {

        return addressService.addAddress(request);
    }

    @GetMapping("/user/{userId}")
    public List<AddressResponse> getUserAddresses(@PathVariable Long userId) {

        return addressService.getUserAddresses(userId);
    }

    @GetMapping("/{id}")
    public AddressResponse getAddress(@PathVariable Long id) {

        return addressService.getAddress(id);
    }

    @PutMapping("/{id}")
    public AddressResponse updateAddress(@PathVariable Long id,
                                         @Valid @RequestBody AddressRequest request) {

        return addressService.updateAddress(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteAddress(@PathVariable Long id) {

        addressService.deleteAddress(id);

        return "Address Deleted Successfully";
    }

    @PutMapping("/{id}/default")
    public AddressResponse setDefaultAddress(@PathVariable Long id) {

        return addressService.setDefaultAddress(id);
    }
}