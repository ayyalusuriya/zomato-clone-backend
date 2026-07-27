package com.zomato.service.impl;

import com.zomato.dto.AddressRequest;
import com.zomato.dto.AddressResponse;
import com.zomato.entity.Address;
import com.zomato.entity.User;
import com.zomato.repository.AddressRepository;
import com.zomato.repository.UserRepository;
import com.zomato.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Override
    public AddressResponse addAddress(AddressRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Address address = Address.builder()
                .addressLine1(request.getAddressLine1())
                .addressLine2(request.getAddressLine2())
                .city(request.getCity())
                .state(request.getState())
                .pincode(request.getPincode())
                .landmark(request.getLandmark())
                .isDefault(false)
                .user(user)
                .build();

        return map(addressRepository.save(address));
    }

    @Override
    public List<AddressResponse> getUserAddresses(Long userId) {

        return addressRepository.findByUserId(userId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public AddressResponse getAddress(Long id) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        return map(address);
    }

    @Override
    public AddressResponse updateAddress(Long id, AddressRequest request) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        address.setAddressLine1(request.getAddressLine1());
        address.setAddressLine2(request.getAddressLine2());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setPincode(request.getPincode());
        address.setLandmark(request.getLandmark());

        return map(addressRepository.save(address));
    }

    @Override
    public void deleteAddress(Long id) {

        addressRepository.deleteById(id);

    }

    @Override
    public AddressResponse setDefaultAddress(Long id) {

        Address selected = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        List<Address> addresses =
                addressRepository.findByUserId(selected.getUser().getId());

        for (Address address : addresses) {
            address.setIsDefault(false);
            addressRepository.save(address);
        }

        selected.setIsDefault(true);

        return map(addressRepository.save(selected));
    }

    private AddressResponse map(Address address) {

        return AddressResponse.builder()
                .id(address.getId())
                .userId(address.getUser().getId())
                .userName(address.getUser().getFullName())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .state(address.getState())
                .pincode(address.getPincode())
                .landmark(address.getLandmark())
                .isDefault(address.getIsDefault())
                .build();
    }
}