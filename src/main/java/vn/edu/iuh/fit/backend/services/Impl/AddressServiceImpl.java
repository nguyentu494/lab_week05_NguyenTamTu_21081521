/*
 * @ (#) AddressServiceImpl.java   1.0     10/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Address;
import vn.edu.iuh.fit.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.backend.services.AddressService;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 10/11/2024
 * @version: 1.0
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address findAddressById(long id) {
        return addressRepository.findAddressById(id);
    }
}
