/*
 * @ (#) AccountService.java   1.0     10/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.services;

import vn.edu.iuh.fit.backend.models.Account;
import vn.edu.iuh.fit.backend.models.Candidate;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 10/11/2024
 * @version: 1.0
 */
public interface AccountService {
    Account checkLogin(String username, String password);
}
