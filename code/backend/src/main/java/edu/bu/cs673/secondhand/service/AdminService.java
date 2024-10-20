package edu.bu.cs673.secondhand.service;

import edu.bu.cs673.secondhand.domain.Admin;
import edu.bu.cs673.secondhand.vo.PageVo;

/**
 * AdminService interface defines the contract for administrative operations.
 * This service provides methods for admin authentication, retrieval, and creation.
 */
public interface AdminService {
    
    /**
     * Authenticates an admin user.
     *
     * @param accountNumber The account number of the admin
     * @param adminPassword The password of the admin
     * @return Admin object if authentication is successful, null otherwise
     */
    Admin login(String accountNumber, String adminPassword);

    /**
     * Retrieves a paginated list of admin users.
     *
     * @param page The page number to retrieve (1-based index)
     * @param nums The number of items per page
     * @return PageVo object containing the list of Admin objects and pagination information
     */
    PageVo<Admin> getAdminList(int page, int nums);

    /**
     * Adds a new admin user to the system.
     *
     * @param adminModel The Admin object containing the details of the new admin
     * @return true if the admin was successfully added, false otherwise
     */
    boolean addAdmin(Admin adminModel);
}





